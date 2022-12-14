package model;

import java.io.Serializable;

public enum Commands implements Serializable {
    ADD,
    REMOVE,
    GET_LIST,
    UPDATE,
    GET_FREE_TIME,
    DOCTOR_EXISTENCE_CHECK,
    CREATE_TICKET
}
