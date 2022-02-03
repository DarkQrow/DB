package Controllers;

public class PassengerAsk {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String number;
    private String pas_num;
    private String pas_ser;


    public PassengerAsk(String id,String firstName,String secondName,String lastName,String number,String pas_num,String pas_ser){
        this.id = id;
        this.firstName = firstName;
        this.lastName = secondName;
        this.middleName = lastName;
        this.number = number;
        this.pas_num = pas_num;
        this.pas_ser = pas_ser;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNumber() {
        return number;
    }

    public String getPas_num() {
        return pas_num;
    }

    public String getPas_ser() {
        return pas_ser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public void setMiddleName(String lastName) {
        this.middleName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPas_num(String pas_num) {
        this.pas_num = pas_num;
    }

    public void setPas_ser(String pas_ser) {
        this.pas_ser = pas_ser;
    }
}
