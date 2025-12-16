package Group2.MyMemory.repository;

import org.springframework.data.domain.Page; // New Import
import org.springframework.data.domain.Pageable; // New Import
import org.springframework.data.jpa.repository.JpaRepository;
import Group2.MyMemory.entity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    // Custom method to fetch memories for a user with pagination
    Page<Memory> findByUserId(Long userId, Pageable pageable); // <--- NEW METHOD
}