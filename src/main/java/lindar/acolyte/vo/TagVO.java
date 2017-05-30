package lindar.acolyte.vo;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagVO implements Serializable {

    private static final long serialVersionUID = 1923703447810434384L;
    
    private String text;
    private String color;
    private String shape;
    private String size;
    private boolean hasArrow;
    
}
