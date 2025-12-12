package Group2.MyMemory.dto;

import lombok.Data;

@Data
public class MemoryRequest {
    
    private String title;
    private String imageUrl;
    private String content;
    private Long categoryId;
    
    // userId is excluded; it comes from the JWT token.
}