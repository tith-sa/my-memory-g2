package Group2.MyMemory.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false) 
    private String username;

    @Column(unique = true, nullable = false) 
    private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt; 

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}
