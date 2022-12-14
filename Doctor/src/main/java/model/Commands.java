package model;

import java.io.Serializable;

public enum Commands implements Serializable {
    ADD,
    REMOVE,
    EDIT,
    GET_LIST,
    UPDATE,
    GET_DOCTOR_LIST,
    GET_TICKET_LIST,
    GET_DOCTOR_TICKET,
    GET_PATIENT_LIST,
    DOCTOR_EXISTENCE_CHECK
}
