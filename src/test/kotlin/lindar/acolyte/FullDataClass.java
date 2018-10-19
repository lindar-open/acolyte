package lindar.acolyte;

import lombok.Getter;
import lombok.Setter;

public class FullDataClass {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private int userId;

    private String coreMsg;

    public FullDataClass(String name, String body, int id, String title, int userId){
        this.name=name;
        this.body=body;
        this.id=id;
        this.title=title;
        this.userId=userId;
    }

}
