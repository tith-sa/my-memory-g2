
package Group2.MyMemory.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;    



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRequest {
    private String tittle;
    private String content;
    private Long categoryId;
    private String imageUrl ;    
}