
package Group2.MyMemory.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;    



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRequest {
    private String title;
    private String content;
    private Long category;
    private String imageUrl ;    
}