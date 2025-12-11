package Group2.MyMemory.service.ServiceImpl;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import Group2.MyMemory.entity.Category;
import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.CategoryRepository;
import Group2.MyMemory.repository.MemoryRepository;
import Group2.MyMemory.repository.UserRepository;
import Group2.MyMemory.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryServiceImpl implements MemoryService {

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository; 

    @Override
    public MemoryResponse createMemory(MemoryRequest request) {
        // 1. Fetch User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        // 2. Fetch Category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        Memory memory = new Memory();
        memory.setTittle(request.getTittle());
        memory.setContent(request.getContent());
        memory.setImageUrl(request.getImageUrl());
        
        // 3. Set the Category OBJECT
        memory.setCategory(category); 
        
        // 4. Set the User OBJECT
        memory.setUser(user); 
        
        memory.setCreatedAt(LocalDateTime.now());
        memory.setUpdatedAt(LocalDateTime.now());

        Memory savedMemory = memoryRepository.save(memory);
        return mapToResponse(savedMemory);
    }
    
    @Override
    public MemoryResponse getMemoryById(Long id) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found with id: " + id));
        return mapToResponse(memory);
    }

    @Override
    public List<MemoryResponse> getAllMemories() {
        return memoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemoryResponse> getMemoriesByUserId(Long userId) {
        return memoryRepository.findByUserId(userId).stream() 
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MemoryResponse updateMemory(Long id, MemoryRequest request) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found with id: " + id));

        // Update Category only if a new ID is provided and it's different
        if (request.getCategoryId() != null && !request.getCategoryId().equals(memory.getCategory().getId())) {
             Category newCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
             memory.setCategory(newCategory);
        }
        
        memory.setTittle(request.getTittle());
        memory.setContent(request.getContent());
        memory.setImageUrl(request.getImageUrl());
        memory.setUpdatedAt(LocalDateTime.now());

        Memory updatedMemory = memoryRepository.save(memory);
        return mapToResponse(updatedMemory);
    }

    @Override
    public void deleteMemory(Long id) {
        if (!memoryRepository.existsById(id)) {
            throw new RuntimeException("Memory not found with id: " + id);
        }
        memoryRepository.deleteById(id);
    }

    // Helper method to convert Entity to DTO (CRITICAL FOR DISPLAYING CATEGORY NAME)
    private MemoryResponse mapToResponse(Memory memory) {
        MemoryResponse response = new MemoryResponse();
        response.setId(memory.getId());
        response.setTittle(memory.getTittle());
        response.setContent(memory.getContent());
        response.setImageUrl(memory.getImageUrl());
        
        // Set Category ID and Name
        if (memory.getCategory() != null) {
             response.setCategoryId(memory.getCategory().getId()); 
             response.setCategoryName(memory.getCategory().getName());
        }
       
        // Set userId from the User object
        response.setUserId(memory.getUser().getId()); 
        
        response.setCreatedAt(memory.getCreatedAt());
        response.setUpdatedAt(memory.getUpdatedAt());
        return response;
    }
}