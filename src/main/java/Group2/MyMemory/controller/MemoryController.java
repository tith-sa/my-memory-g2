package Group2.MyMemory.controller;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import Group2.MyMemory.service.MemoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryService memoryService;

    // Constructor injection
    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    
    @PostMapping
    public ResponseEntity<MemoryResponse> createMemory(@RequestBody MemoryRequest request) {
        try {
            MemoryResponse response = memoryService.createMemory(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<MemoryResponse> getMemoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memoryService.getMemoryById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MemoryResponse>> getAllMemories() {
        return ResponseEntity.ok(memoryService.getAllMemories());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MemoryResponse>> getMemoriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(memoryService.getMemoriesByUserId(userId));
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<MemoryResponse> updateMemory(@PathVariable Long id, @RequestBody MemoryRequest request) {
        try {
            return ResponseEntity.ok(memoryService.updateMemory(id, request));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        try {
            memoryService.deleteMemory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}