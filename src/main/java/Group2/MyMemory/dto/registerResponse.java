package Group2.MyMemory.dto;

public class registerResponse {
    private String token;
    private String username;
    private String email;
    private String password;
	private Long id;
    
    public  registerResponse(String token,Long id, String username, String email, String password) {
        this.token = token;
        this.id = id;
        this.username = username;
		this.email = email;
		this.password = password;
    }

   
}
