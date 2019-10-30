public class Students {
    private int sid,studid;
    private String firstname,surname;

    public Students() {
    }

    public Students(int sid, int studid, String firstname, String surname) {
        this.sid = sid;
        this.studid = studid;
        this.firstname = firstname;
        this.surname = surname;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setStudid(int studid) {
        this.studid = studid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSid() {
        return sid;
    }

    public int getStudid() {
        return studid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }
}
