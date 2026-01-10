// package Group2.MyMemory.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.Table;
// import lombok.Data;

// @Entity
// @Table(name = "users")
// @Data
// public class User {
	
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long id;
	
// 	@Column(unique = true, nullable = false) 
//     private String username;

//     @Column(unique = true, nullable = false) 
//     private String email;
	
// 	@Column(nullable = false)
// 	private String password;

// 	@Column(name = "created_at", nullable = false, updatable = false)
// 	private LocalDateTime createdAt; 

// 	@Column(name = "updated_at", nullable = false)
// 	private LocalDateTime updatedAt;
	
// 	@PrePersist
//     protected void onCreate() {
//         this.createdAt = LocalDateTime.now();
// 		this.updatedAt = LocalDateTime.now();
//     }

// }
package Group2.MyMemory.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false) 
    private String username;

    @Column(unique = true, nullable = false) 
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    // Removed the 'role' field

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; 

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // -------------------------------------------------------------------
    // UserDetails Methods (Mandatory for Spring Security)
    // -------------------------------------------------------------------
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 🛑 FIX: Returns a single, generic authority if no roles are used.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public String getUsername() {
        // IMPORTANT: Returns the User ID as a String for the security principal
        return this.id.toString(); 
    }

    @Override
    public String getPassword() {
        // Getter for password provided by @Data
        return this.password;
    }
    
    // Default True for simplicity
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}