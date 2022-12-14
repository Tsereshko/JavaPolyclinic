package model;

import java.io.Serializable;

public enum Commands implements Serializable {
    ADD,
    REMOVE,
    GET_LIST,
    UPDATE,
    GET_DEPARTMENT_DOCTOR_LIST,
    GET_FREE_TIME,
    GET_TICKET_LIST,
    GET_DOCTOR_TICKET,
    GET_PATIENT_LIST,
    DOCTOR_EXISTENCE_CHECK,
    CREATE_TICKET
}
