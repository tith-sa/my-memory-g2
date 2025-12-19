package Group2.MyMemory.controller;

import Group2.MyMemory.entity.Memory;
import Group2.MyMemory.service.MemoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("/search")
    public List<Memory> searchMemories(@RequestParam String keyword) {
        return memoryService.searchMemories(keyword);
    }
}
