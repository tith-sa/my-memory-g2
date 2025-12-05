package Group2.MyMemory.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Data
public class RegisterRequest {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 6, message = "Massword maust be at least 6 charactors")
	private String password;

}
