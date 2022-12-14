package model;

import java.io.Serial;
import java.io.Serializable;

public class Analysis implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String result;
}
