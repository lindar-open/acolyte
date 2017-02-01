package lindar.acolyte.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessCredentials {
    private String apiUrl;
    private String sessionCookie;

    private String username;
    private String password;
    
    public AccessCredentials(String apiUrl, String username, String password) {
        this.apiUrl = apiUrl;
        this.username = username;
        this.password = password;
    }
    
    public AccessCredentials(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }
}
