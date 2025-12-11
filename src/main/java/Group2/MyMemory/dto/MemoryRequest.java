package Group2.MyMemory.dto;

import lombok.Data;

@Data
public class MemoryRequest {
    
    private String tittle;
    private String imageUrl;
    private String content;

    private Long categoryId; // Used to look up Category entity
    private Long userId;     // Used to look up User entity

    // private List<Long> tagIds; // Include if implementing tags
}