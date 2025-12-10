import Group2.MyMemory.entity.Memory;


import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import java.util.List;


public interface MemoryService{
    MemoryResponse createMemory(MemoryRequest memoryRequest, Long userId);
    MemoryResponse getMemoryById(Long memoryId);
    List<MemoryResponse> getAllMemoriesByUserId(Long userId);
    MemoryResponse updateMemory(Long memoryId, MemoryRequest memoryRequest);
    void deleteMemory(Long memoryId);   

}