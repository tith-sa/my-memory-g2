package Group2.MyMemory.service;

import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.repository.MemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public List<Memory> searchMemories(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new RuntimeException("Keyword must not be empty");
        }

        List<Memory> result = memoryRepository.findByTitleContainingIgnoreCase(keyword);
        List<Memory> byCategory = memoryRepository.findByCategory_NameContainingIgnoreCase(keyword);

        for (Memory m : byCategory) {
            if (!result.contains(m)) {
                result.add(m);
            }
        }

        if (result.isEmpty()) {
            throw new RuntimeException("No memories found for this keyword");
        }

        boolean hasNoCategory = result.stream().anyMatch(m -> m.getCategory() == null);
        if (hasNoCategory) {
            throw new RuntimeException("Category must not be empty");
        }

        return result;
    }
}
