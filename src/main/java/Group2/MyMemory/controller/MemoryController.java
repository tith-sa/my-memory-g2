package Group2.MyMemory.controller;

import org.springframework.data.domain.Page; // New Import
import org.springframework.data.domain.Pageable; // New Import
import org.springframework.data.domain.Sort; // New Import
import org.springframework.data.web.PageableDefault; // New Import
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group2.MyMemory.dto.memory.MemoryRequest;
import Group2.MyMemory.dto.memory.MemoryResponse;
import Group2.MyMemory.service.MemoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;

    // CREATE: POST /api/memories/ (Requires categoryId in body)
    @PostMapping
    public ResponseEntity<MemoryResponse> createMemory(
            @RequestBody MemoryRequest request,
            @RequestHeader("Authorization") String token
    ) {
        try {
            MemoryResponse response = memoryService.createMemory(request, token);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // READ ALL (PAGINATED): GET /api/memories?page=0&size=10&sort=createdAt,desc
    @GetMapping // <--- UPDATED METHOD
    public ResponseEntity<Page<MemoryResponse>> getAllUserMemories(
            @RequestHeader("Authorization") String token,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        try {
            Page<MemoryResponse> memories = memoryService.getAllUserMemories(token, pageable);
            return ResponseEntity.ok(memories);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // READ SINGLE: GET /api/memories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MemoryResponse> getMemoryById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        try {
            MemoryResponse response = memoryService.getMemoryById(id, token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // UPDATE: PUT /api/memories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MemoryResponse> updateMemory(
            @PathVariable Long id,
            @RequestBody MemoryRequest request,
            @RequestHeader("Authorization") String token
    ) {
        try {
            MemoryResponse response = memoryService.updateMemory(id, request, token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // DELETE: DELETE /api/memories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        try {
            memoryService.deleteMemory(id, token);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}