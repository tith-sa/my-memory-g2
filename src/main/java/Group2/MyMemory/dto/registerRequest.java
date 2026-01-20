package Group2.MyMemory.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class registerRequest {
	    private String username;
	    private String email;
	    private String password;
		private Long id;
}
