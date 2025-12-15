package Group2.MyMemory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "memories")

public class Memory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")  
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // Many memories belong to one category
    @JoinColumn(name = "category_id", nullable = false) // Specifies the foreign key
    private Category category;

   @ManyToOne(fetch = FetchType.LAZY) // Many memories belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Specifies the foreign key column name in the 'memories' table
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt; 

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
    }

}