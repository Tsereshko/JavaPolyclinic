package Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ServiceUtil {

    public static byte[] getSHA(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private static void typeHanding(Object object){
        if(object instanceof Doctor){
            Doctor doctor = (Doctor) object;
            doctor.setPassword(toHexString(getSHA(doctor.getPassword())));
        } else if (object instanceof Ticket) {
            //Создание PDF
            Ticket ticket = (Ticket) object;
            Document document = new Document();
            try
            {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
                document.open();
                Font font = new Font();
                Font ticketFont = new Font();
                ticketFont.setSize(25);
                font.setSize(100);
                document.add(new Paragraph("Ticket", font));

                document.add(new Paragraph("\ntime: " + ticket.getTime(), ticketFont));
                document.add(new Paragraph("\ndate: " + ticket.getDate(), ticketFont));
                document.add(new Paragraph("\npatient: " + ticket.getPatient().getName() + " "
                        + ticket.getPatient().getSurname() + " "
                        + ticket.getPatient().getPatronymic() + " "
                        , ticketFont));
                document.add(new Paragraph("\npatient: " + ticket.getPatient().getName() + " "
                        + ticket.getDoctor().getName() + " "
                        + ticket.getDoctor().getSurname() + " "
                        +ticket.getDoctor().getPatronymic()
                        , ticketFont));

                document.close();
                writer.close();
            } catch (DocumentException e)
            {
                e.printStackTrace();
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void insert(Object obj) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        typeHanding(obj);
        session.save(obj);
        tx1.commit();
        session.close();
    }

    public static void remove(List list) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (Object obj : list) {
            session.delete(obj);
        }
        tx1.commit();
        session.close();
    }

    public static void update(Object obj) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        typeHanding(obj);
        session.update(obj);
        tx1.commit();
        session.close();
    }

    public static <T> List findAll(ClassType type) {
        String queryString = "";
        switch (type) {
            case DEPARTMENT -> queryString = "From Department";
            case DOCTOR ->queryString = "From Doctor";
            case PATIENT ->queryString = "From Patient";
            case TICKET ->queryString = "From Ticket ";
        }
        return (List<T>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(queryString).list();
    }

    public static List<Doctor> findAllDoctor(Department department) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Doctor where department = :paramName"
        );
        query.setParameter("paramName", department);
        return query.list();
    }
    public static List<Ticket> findAllTicket(List<Doctor> doctorList, String date) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Ticket where doctor = :doctor and date = :date"
        );
        List<Ticket> ticketList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            query.setParameter("doctor", doctor);
            query.setParameter("date", date);
            List<Ticket> oneList = query.list();
            ticketList.addAll(oneList);
        }
        return ticketList;
    }

    public static List<Ticket> findAllTicket(Doctor doctor) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Ticket where doctor = :doctor"
        );
        query.setParameter("doctor", doctor);
        List<Ticket> list = query.list();
        System.out.println(list);
        return list;
    }

    //Поиск всех данных о пациенте
    public static List findPatientList(Patient patient, ClassType type) {
        String strQuery = "";

        switch (type) {
            case ANALYSIS -> strQuery = "from Analysis where patient = :patient";
            case CONCLUSION -> strQuery = "from Conclusion where patient = :patient";
            case TREATMENT -> strQuery = "from Treatment where patient = :patient";
        }
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                strQuery
        );
        query.setParameter("patient", patient);
        return query.list();
    }

    //проверка на повтор логина и пароля врача
    public static Doctor doctorCheck(String username, String password) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "from Doctor where username = :username and password = :password"
        );
        query.setParameter("username", username);
        query.setParameter("password", toHexString(getSHA(password)));
        Doctor doctor = (Doctor) query.getSingleResultOrNull();
        System.out.println(doctor);
        return doctor;
    }




    public static List<String> findFreeTime(String date, Department department) {
        List<Doctor> doctorList = findAllDoctor(department);
        List<Ticket> ticketList = findAllTicket(doctorList, date);
        List<String> strList = new ArrayList<>();

        for (int h = 0; h < 8; h++) {
            for (int m = 0; m < 4; m++) {
                String time = h + 8 + ":" + m * 15;
                //if the count of time ticket is equal to the count of doctors, then there is no free time ticket
                if (countingTicket(ticketList,h + 8 + ":" + m * 15) != doctorList.size()) strList.add(time);
            }
        }
        return strList;
    }
    private static int countingTicket(List<Ticket> ticketList, String time) {
        int count = 0;
        for (Ticket ticket : ticketList) {
            System.out.println(ticket.getTime() + " " + time);
            if (Objects.equals(ticket.getTime(), time)) count++;
        }
        return count;
    }
    public static boolean createTicket(String date, Department department, String time, Patient patient) {
        List<Doctor> departmentDoctorList = findAllDoctor(department);
        //список талонов каждого врача отделения на указанное время
        List<Ticket> ticketList = findAllTicket(departmentDoctorList, date);
        //получаем врачей, у которых уже имеется талон на указанное время
        List<Doctor> timeDoctorList = getListTimeDoctor(ticketList, time);

        //Если находится такой элемент из списка врачей с талоном на указанное время,
        //которого нет в списке всех врачей отделения, то талон привязывается к этому врачу
        for (Doctor doctor: departmentDoctorList) {
            if(!timeDoctorList.contains(doctor)) {
                insert(new Ticket(time, date, doctor, patient));
                return true;
            }
        }
        return false;
    }
    private static List<Doctor> getListTimeDoctor(List<Ticket> ticketList, String time) {
        List<Doctor> listDoctor = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (Objects.equals(ticket.getTime(), time)) {
                listDoctor.add(ticket.getDoctor());
            }
        }
        return listDoctor;
    }
}
