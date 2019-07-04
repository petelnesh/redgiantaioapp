package com.keredgiantaio.techsavanna.redgiantaio.methods;

public class Sweep {
    String id;
    String date;
    String name;
    String type;
    String closed_on;

    public Sweep(String id, String date, String name, String type, String closed_on) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.type = type;
        this.closed_on = closed_on;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClosed_on() {
        return closed_on;
    }

    public void setClosed_on(String closed_on) {
        this.closed_on = closed_on;
    }
}
