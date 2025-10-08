package anjie.yu;

import java.util.Date;

public class Assignment {
    private String name;
    private String subject;
    private Date dueDate;
    private Double duration;

    public Assignment () {
        name = "";
        subject = "";
        dueDate = new Date();
        duration = 0.0;
    }

    public Assignment (String n, String s, Date dd, Double du) {
        name = n;
        subject = s;
        dueDate = dd;
        duration = du;
    }

    public String getName () {
        return name;
    }

    public void setName (String n) {
        name = n;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject (String s) {
        subject = s;
    }

    public Date getDueDate () {
        return dueDate;
    }

    public void setDueDate (Date dd) {
        dueDate = dd;
    }

    public Double getDuration () {
        return duration;
    }

    public void setDuration (Double du) {
        duration = du;
    }
}
