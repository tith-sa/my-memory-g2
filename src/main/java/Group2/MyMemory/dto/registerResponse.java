package Group2.MyMemory.dto;

public class registerResponse {

    private String username;
	    private String email;
	    private String password;
		private Long id;
    public  registerResponse(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
		this.email = email;
		this.password = password;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
}
