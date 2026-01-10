package Group2.MyMemory.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MemoryResponse {
    
    private Long id;
    private String title;
    private String imageUrl;
    private String content;

    private Long categoryId;
    private String categoryName;

    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}