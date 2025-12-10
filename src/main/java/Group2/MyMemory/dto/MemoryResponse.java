package Group2.MyMemory.dto;
import lombok.Data;
import java.util.List;



@Data

public class MemoryResponse{

    private Long id;
    private String tittle;
    private List<String> content;
    private List<Long> categoryId;

   
}
    