package Group2.MyMemory.service;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import java.util.List;

public interface MemoryService {
    
    // Signature accepts userId from the token via the controller
    MemoryResponse createMemory(Long userId, MemoryRequest request); 
    
    MemoryResponse getMemoryById(Long id);
    List<MemoryResponse> getAllMemories();
    List<MemoryResponse> getMemoriesByUserId(Long userId);
    MemoryResponse updateMemory(Long id, MemoryRequest request);
    void deleteMemory(Long id);
}