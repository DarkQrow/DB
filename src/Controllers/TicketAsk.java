package Controllers;

public class TicketAsk {

    private String from;
    private String to;
    private String start;
    private String end;
    private String klass;
    private int price;

    public TicketAsk(String from,String to,String start,String end,String klass,int price){

        this.from = from;
        this.to = to;
        this.start = start;
        this.end = end;
        this.price = price;
        this.klass = klass;
    }
    public String getKlass(){return klass;}
    public int getPrice() {
        return price;
    }

    public String getEnd() {
        return end;
    }

    public String getFrom() {
        return from;
    }

    public String getStart() {
        return start;
    }

    public String getTo() {
        return to;
    }

    public void setKlass(String klass) {this.klass = klass;}

    public void setEnd(String end) {
        this.end = end;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
