package lindar.acolyte.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCredentials {
    private String apiUrl;

    private String username;
    private String password;
}
