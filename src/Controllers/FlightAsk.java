package Controllers;

public class FlightAsk {
    private String id;

    private String basePrice;
    private String departureTime;
    private String arrivalTime;
    private String airportDepartureId;
    private String airportArrivalId;
    private String planeId;
    public FlightAsk(String id, String basePrice, String departureTime, String arrivalTime, String airportDepartureId,String airportArrivalId,String planeId){
        this.id = id;

        this.basePrice = basePrice;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airportDepartureId = airportDepartureId;
        this.airportArrivalId = airportArrivalId;
        this.planeId = planeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirportArrivalId() {
        return airportArrivalId;
    }

    public String getAirportDepartureId() {
        return airportDepartureId;
    }

    public String  getBasePrice() {
        return basePrice;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }


    public String getPlaneId() {
        return planeId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setAirportArrivalId(String airportArrivalId) {
        this.airportArrivalId = airportArrivalId;
    }

    public void setAirportDepartureId(String airportDepartureId) {
        this.airportDepartureId = airportDepartureId;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }



}
