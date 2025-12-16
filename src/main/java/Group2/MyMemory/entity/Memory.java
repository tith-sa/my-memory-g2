package Group2.MyMemory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; // New Import
import jakarta.persistence.JoinColumn; // New Import
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // A memory belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // A memory belongs to one category (Many-to-One relationship)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // <--- NEW FIELD

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}