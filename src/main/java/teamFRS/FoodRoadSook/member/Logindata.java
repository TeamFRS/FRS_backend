package teamFRS.FoodRoadSook.member;

import lombok.Data;

@Data
public class Logindata {
    String id;
    String pw;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
