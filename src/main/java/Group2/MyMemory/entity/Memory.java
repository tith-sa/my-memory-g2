package Group2.MyMemory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
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

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")  
    private String content;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}