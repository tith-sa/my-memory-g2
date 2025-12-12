package Group2.MyMemory.controller;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import Group2.MyMemory.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;
    
    /**
     * Helper method to safely extract the Long User ID from the Security Context (Token).
     */
    private Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required.");
        }
        
        // ASSUMPTION: The principal (authentication.getName()) is the User ID as a String.
        try {
            return Long.parseLong(authentication.getName());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user principal format. Cannot identify user ID.");
        }
    }

    /**
     * POST /api/memories : Create a new Memory. Requires JWT Token.
     */
    @PostMapping
    public ResponseEntity<MemoryResponse> createMemory(
            @RequestBody MemoryRequest request,
            Authentication authentication) {
        
        // 1. Get the authenticated user's ID from the token (enforces 401 if token is bad)
        Long userId = getUserIdFromAuthentication(authentication);

        try {
            // 2. Pass the inferred userId and the request body to the service
            MemoryResponse response = memoryService.createMemory(userId, request); 
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * GET /api/memories/{id} : Get a Memory by its ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemoryResponse> getMemoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memoryService.getMemoryById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}