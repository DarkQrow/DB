package Controllers;

public class CityAsk {
    private String id;
    private String name;
    private String cityId;

    CityAsk(String id, String name, String cityId){
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
