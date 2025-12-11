package Group2.MyMemory.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MemoryResponse {
    
    private Long id;
    private String tittle;
    private String imageUrl;
    private String content;

    private Long categoryId;
    private String categoryName; // Displays the connected category name

    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}