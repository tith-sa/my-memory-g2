package Group2.MyMemory.controller;

import java.util.List;

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

    // --- CREATE ---
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

    // --- READ ALL (By User) ---
    @GetMapping
    public ResponseEntity<List<MemoryResponse>> getAllUserMemories(
            @RequestHeader("Authorization") String token
    ) {
        try {
            List<MemoryResponse> memories = memoryService.getAllUserMemories(token);
            return ResponseEntity.ok(memories);
        } catch (RuntimeException e) {
            // Handle cases where token is invalid or user doesn't exist
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // --- READ SINGLE ---
    @GetMapping("/{id}")
    public ResponseEntity<MemoryResponse> getMemoryById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        try {
            MemoryResponse response = memoryService.getMemoryById(id, token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Memory not found
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Access denied
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Token issue
        }
    }

    // --- UPDATE ---
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

    // --- DELETE ---
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