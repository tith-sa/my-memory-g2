package Group2.MyMemory.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Group2.MyMemory.entity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    // Find all memories belonging to a specific user ID
    List<Memory> findByUserId(Long userId);
}