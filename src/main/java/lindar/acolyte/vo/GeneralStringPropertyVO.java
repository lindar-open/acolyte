package lindar.acolyte.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralStringPropertyVO implements Serializable {

    private static final long serialVersionUID = -4139837793756621395L;

    private String code;
    private String wording;
}
