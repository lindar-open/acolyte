package lindar.acolyte.vo;

import java.util.Date;
import lombok.Data;

@Data
public class MessageVO {
    
    private String chatRoom;
    
    private String username;
    
    private String body;
    
    private Date createdAt;
    
}
