package Controllers;

public class AiroprtsAsk {
    private String id;
    private String name;
    private String site;
    private String cityId;
    private String code;

    AiroprtsAsk(String id, String name,String site,String cityId,String code){
        this.id = id;
        this.name = name;
        this.site = site;
        this.cityId = cityId;
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
