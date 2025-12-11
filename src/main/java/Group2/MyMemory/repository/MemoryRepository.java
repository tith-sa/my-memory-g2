package Group2.MyMemory.repository;

import Group2.MyMemory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    // Custom query to find memories by specific user
    List<Memory> findByUserId(Long userId);
}