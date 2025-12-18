package Group2.MyMemory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group2.MyMemory.dto.MemoryRequest;
import Group2.MyMemory.dto.MemoryResponse;
import Group2.MyMemory.service.MemoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @PostMapping("/create")
    public ResponseEntity<MemoryResponse> createMemory(@RequestBody MemoryRequest request) {
   
         MemoryResponse response = memoryService.createMemory(request);
        return ResponseEntity.ok(response);
   
    
}

    @GetMapping("/get/{memoryId}")
    public ResponseEntity<MemoryResponse> getMemoryById(@PathVariable("memoryId") Long memoryId) {
        
            MemoryResponse response = memoryService.getMemoryById(memoryId);
            return ResponseEntity.ok(response);
    }
    
    
}