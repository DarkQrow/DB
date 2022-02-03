package Controllers;

public class CountryAsk {
    private String id;
    private String name;
    private String code;

    public CountryAsk(String id,String name,String code){
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
