package lindar.acolyte.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Password {
    private String value;
    private String salt;
}
