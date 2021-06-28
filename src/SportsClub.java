import java.io.Serializable;

public abstract class SportsClub implements Serializable {
    private String name;
    private String location;


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
