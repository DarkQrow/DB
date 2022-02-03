package Controllers;

public class BroneAsk {
    private String passengerId;
    private String ticketId;
    public BroneAsk(String passengerId,String ticketId){
        this.passengerId = passengerId;
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
