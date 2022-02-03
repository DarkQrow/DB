package Controllers;

public class SeatAsk {
private String id;
private String klass;
private String ccount;
private String seatNumber;
private String Busyiness;
private String planeId;

public SeatAsk(String id,String klass, String ccount,String seatNumber, String Busyiness, String planeId){
    this.id = id;
    this.klass = klass;
    this.ccount = ccount;
    this.seatNumber = seatNumber;
    this.Busyiness = Busyiness;
    this.planeId = planeId;
}

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBusyiness() {
        return Busyiness;
    }

    public String getCcount() {
        return ccount;
    }

    public String getKlass() {
        return klass;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setBusyiness(String busyiness) {
        Busyiness = busyiness;
    }

    public void setCcount(String ccount) {
        this.ccount = ccount;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
