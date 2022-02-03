package Controllers;

public class TicketAdAsk {
    private String id;
    private String price;
    private String flightId;
    public TicketAdAsk(String id,String price,String flightId){
        this.id = id;
        this.price = price;
        this.flightId = flightId;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
