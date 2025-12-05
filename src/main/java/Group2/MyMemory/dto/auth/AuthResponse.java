package Group2.MyMemory.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String message;
	private Long userId;
	

}
