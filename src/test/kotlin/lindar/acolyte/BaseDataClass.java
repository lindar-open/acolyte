package lindar.acolyte;


import lombok.Getter;
import lombok.Setter;

public class BaseDataClass {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    private int id;

    public BaseDataClass(String name, String body, int id){
        this.name = name;
        this.body = body;
        this.id = id;
    }

}
