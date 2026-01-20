package Group2.MyMemory.dto.memory;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;
    private Long categoryId;   // <--- NEW FIELD
    private String categoryName; // <--- NEW FIELD
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}