package Group2.MyMemory.service;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Group2.MyMemory.dto.memory.MemoryRequest;
import Group2.MyMemory.dto.memory.MemoryResponse;
import Group2.MyMemory.entity.Category;
import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.CategoryRepository; // New Import
import Group2.MyMemory.repository.MemoryRepository;
import Group2.MyMemory.repository.UserRepository;
import Group2.MyMemory.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryService {

	private final MemoryRepository memoryRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository; // <--- NEW INJECTION
	private final JwtUtil jwtUtil;

	// Helper method to convert Entity to Response DTO (UPDATED)
	private MemoryResponse mapToResponse(Memory memory) {
		return new MemoryResponse(
				memory.getId(),
				memory.getTitle(),
				memory.getContent(),
				memory.getUser().getId(),
				memory.getUser().getUsername(),
				memory.getCategory().getId(),
				memory.getCategory().getName(),
				memory.getCreatedAt(),
				memory.getUpdatedAt()
		);
	}

	private Long getUserIdFromToken(String token) {
		String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
		return jwtUtil.getUserIdFromToken(jwt);
	}

	// CREATE (UPDATED)
	public MemoryResponse createMemory(MemoryRequest request, String token) {
		Long userId = getUserIdFromToken(token);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		// 1. Fetch the Category
		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + request.getCategoryId()));

		Memory memory = new Memory();
		memory.setTitle(request.getTitle());
		memory.setContent(request.getContent());
		memory.setUser(user);
		memory.setCategory(category); // 2. Set the Category

		Memory savedMemory = memoryRepository.save(memory);
		return mapToResponse(savedMemory);
	}

	// READ ALL (PAGINATION) (UPDATED)
	public Page<MemoryResponse> getAllUserMemories(String token, Pageable pageable) {
		Long userId = getUserIdFromToken(token);

		// Use the new repository method with Pageable
		Page<Memory> memoryPage = memoryRepository.findByUserId(userId, pageable);

		// Map the page content to the response DTOs
		return memoryPage.map(this::mapToResponse);
	}

	// READ SINGLE (UPDATED mapToResponse call)
	public MemoryResponse getMemoryById(Long memoryId, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		return mapToResponse(memory);
	}

	// UPDATE (UPDATED to handle Category update)
	public MemoryResponse updateMemory(Long memoryId, MemoryRequest request, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		memory.setTitle(request.getTitle());
		memory.setContent(request.getContent());
		memory.setUpdatedAt(LocalDateTime.now());

		// Handle Category update if ID is provided
		if (request.getCategoryId() != null) {
			Category newCategory = categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + request.getCategoryId()));

			// Only update if the category ID is actually different
			if (!request.getCategoryId().equals(memory.getCategory().getId())) {
				memory.setCategory(newCategory);
			}
		}

		Memory updatedMemory = memoryRepository.save(memory);
		return mapToResponse(updatedMemory);
	}

	// DELETE (No change needed)
	public void deleteMemory(Long memoryId, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		memoryRepository.delete(memory);
	}
}