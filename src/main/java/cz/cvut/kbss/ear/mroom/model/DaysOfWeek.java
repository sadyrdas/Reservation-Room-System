package cz.cvut.kbss.ear.mroom.model;

public enum DaysOfWeek {
    Monday(0),
    Tuesday(1),
    Wednesday(2),
    Thursday(3),
    Friday(4),
    Saturday(5),
    Sunday(6);


    private final int num_of_day;

    DaysOfWeek(int num_of_day) {
        this.num_of_day = num_of_day;
    }

    public int getNum_of_day() {
        return num_of_day;
    }
}
