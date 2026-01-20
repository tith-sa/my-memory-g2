package Group2.MyMemory.dto;

public class loginResponse {
    private String token;
    private Long id;
    private String username;
    private String email;
    private String password;

    public loginResponse(String token, Long id, String username, String email,String password) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getToken() { return token; }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword () {return password;}
}
