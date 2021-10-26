package nordside.web.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTokenPair {

    private String accessToken;
    private String refreshToken;

}
