package Group2.MyMemory.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import Group2.MyMemory.entity.Category;
import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.exception.ResourceNotFoundException;
import Group2.MyMemory.repository.CategoryRepository;
import Group2.MyMemory.repository.MemoryRepository;
import Group2.MyMemory.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository; 

    public MemoryResponse createMemory(MemoryRequest request) {

        // Get userId from JWT authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());

        // Fetch User entity from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Fetch Category entity
        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        // Create and save Memory
        Memory memory = new Memory();
        memory.setTitle(request.getTitle());
        memory.setImageUrl(request.getImageUrl());
        memory.setContent(request.getContent());
        memory.setCategory(category);
        memory.setUser(user);

        Memory savedMemory = memoryRepository.save(memory);

        // Build response
        return new MemoryResponse(
                savedMemory.getId(),
                savedMemory.getTitle(),
                savedMemory.getImageUrl(),
                savedMemory.getContent(),
                savedMemory.getCategory().getId()
        );
    }

    public MemoryResponse getMemoryById(Long memoryId) {
        // Get userId from JWT authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());

        // Fetch Memory entity
        Memory memory = memoryRepository.findByIdAndUserId(memoryId, userId);
        if (memory == null) {
            throw new ResourceNotFoundException("Memory not found");
        }

        // Build response
        return new MemoryResponse(
                memory.getId(),
                memory.getTitle(),
                memory.getImageUrl(),
                memory.getContent(),
                memory.getCategory().getId()
        );
    } 
}
