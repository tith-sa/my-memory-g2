
package Group2.MyMemory.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;    



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryRequest {
    private String tittle;
    private String content;
    private List<Long> categoryId;
    private List<String> imageUrl ;    
}