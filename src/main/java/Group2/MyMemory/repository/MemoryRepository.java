package Group2.MyMemory.repository;

import Group2.MyMemory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    /**
     * Retrieves all Memory entities linked to a specific User ID.
     * Must be findByUser_Id to traverse the 'user' field in Memory and query its 'id'.
     */
    List<Memory> findByUser_Id(Long userId);
}