package Group2.MyMemory.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import Group2.MyMemory.dto.memory.MemoryRequest;
import Group2.MyMemory.dto.memory.MemoryResponse;
import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.MemoryRepository;
import Group2.MyMemory.repository.UserRepository;
import Group2.MyMemory.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryService {

	private final MemoryRepository memoryRepository;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil; // Used to extract user ID from the token

	// Helper method to convert Entity to Response DTO
	private MemoryResponse mapToResponse(Memory memory) {
		return new MemoryResponse(
				memory.getId(),
				memory.getTitle(),
				memory.getContent(),
				memory.getUser().getId(),
				memory.getUser().getUsername(),
				memory.getCreatedAt(),
				memory.getUpdatedAt()
		);
	}

	/**
	 * Extracts the user ID from the JWT string.
	 * @param token The JWT (e.g., "Bearer <token>" or just "<token>")
	 * @return The user ID (Long)
	 * @throws RuntimeException if the user is not found or token is invalid
	 */
	private Long getUserIdFromToken(String token) {
		String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
		return jwtUtil.getUserIdFromToken(jwt);
	}

	// --- CRUD OPERATIONS ---

	// 1. CREATE
	public MemoryResponse createMemory(MemoryRequest request, String token) {
		Long userId = getUserIdFromToken(token);

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		Memory memory = new Memory();
		memory.setTitle(request.getTitle());
		memory.setContent(request.getContent());
		memory.setUser(user);

		Memory savedMemory = memoryRepository.save(memory);
		return mapToResponse(savedMemory);
	}

	// 2. READ (All by user)
	public List<MemoryResponse> getAllUserMemories(String token) {
		Long userId = getUserIdFromToken(token);

		List<Memory> memories = memoryRepository.findByUserId(userId);
		return memories.stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	// 3. READ (Single) - Must belong to the user
	public MemoryResponse getMemoryById(Long memoryId, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		// SECURITY CHECK: Ensure the memory belongs to the authenticated user
		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		return mapToResponse(memory);
	}

	// 4. UPDATE - Must belong to the user
	public MemoryResponse updateMemory(Long memoryId, MemoryRequest request, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		// SECURITY CHECK: Ensure the memory belongs to the authenticated user
		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		memory.setTitle(request.getTitle());
		memory.setContent(request.getContent());
		memory.setUpdatedAt(LocalDateTime.now());

		Memory updatedMemory = memoryRepository.save(memory);
		return mapToResponse(updatedMemory);
	}

	// 5. DELETE - Must belong to the user
	public void deleteMemory(Long memoryId, String token) {
		Long userId = getUserIdFromToken(token);

		Memory memory = memoryRepository.findById(memoryId)
				.orElseThrow(() -> new IllegalArgumentException("Memory not found with ID: " + memoryId));

		// SECURITY CHECK: Ensure the memory belongs to the authenticated user
		if (!memory.getUser().getId().equals(userId)) {
			throw new SecurityException("Access denied: Memory does not belong to the authenticated user.");
		}

		memoryRepository.delete(memory);
	}
}