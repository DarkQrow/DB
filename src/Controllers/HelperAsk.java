package Controllers;

public class HelperAsk {
    private String id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String airportId;

    public HelperAsk(String id,String phoneNumber,String firstName, String lastName,String middleName,String airportId){
        this.id = id;

        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.airportId = airportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAirportId() {
        return airportId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }



    public String getMiddleName() {
        return middleName;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
