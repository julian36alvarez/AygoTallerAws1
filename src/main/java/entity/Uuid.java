package entity;



public class Uuid {
    private String uuidString;
    private String date;


    public Uuid() {
        // Do nothing because of X and Y.
    }

    public String getUuid() {
        return uuidString;
    }

    public String getDate() {
        return date;
    }

    public void setUuid(String uuid) {
        this.uuidString = uuid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Uuid{" + "uuid=" + uuidString + ", date=" + date + '}';
    }
}

