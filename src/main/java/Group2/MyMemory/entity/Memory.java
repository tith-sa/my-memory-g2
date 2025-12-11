package Group2.MyMemory.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;


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

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")  
    private String content;

    // --- Many-to-One Relationship with Category ---
    @ManyToOne(optional = false, fetch = FetchType.EAGER) 
    @JoinColumn(name = "category_id", nullable = false) 
    private Category category; 
    // ----------------------------------------------

    // --- Many-to-One Relationship with User ---
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;  
    // ------------------------------------------

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
        name = "memory_tags",   
        joinColumns = @JoinColumn(name = "memory_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}