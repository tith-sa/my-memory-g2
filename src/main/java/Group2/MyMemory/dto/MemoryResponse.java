package Group2.MyMemory.dto;
import lombok.Data;




@Data

public class MemoryResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private Long categoryId;

    public MemoryResponse(Long id, String title, String imageUrl, String content, Long categoryId) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
        this.categoryId = categoryId;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }        
    public String getContent() { return content; }
    public Long getCategoryId() { return categoryId; }

}

    