
package Group2.MyMemory.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Group2.MyMemory.entity.Memory;


@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    Memory findByIdAndUserId(Long id, Long userId);
    Memory findByTitle(String title);
    Memory findByContent(String content);
    List<Memory> findByCategoryId(Long categoryId);
    

}

    