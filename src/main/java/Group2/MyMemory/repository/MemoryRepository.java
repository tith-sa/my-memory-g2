package Group2.MyMemory.repository;

import Group2.MyMemory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    // Search memories where title contains keyword (case-insensitive)
    List<Memory> findByTitleContainingIgnoreCase(String keyword);

    // Search memories where category name contains keyword (case-insensitive)
    List<Memory> findByCategory_NameContainingIgnoreCase(String keyword);
}
