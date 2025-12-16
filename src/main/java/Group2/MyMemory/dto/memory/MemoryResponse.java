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
    private Long userId; // The ID of the user who owns this memory
    private String username; // Optional: include username for better context
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}