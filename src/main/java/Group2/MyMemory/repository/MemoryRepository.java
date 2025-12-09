
package Group2.MyMemory.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Group2.MyMemory.entity.Memory;


@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    Memory findByIdAndUserId(Long id, Long userId);
    Memory findByTittle(String tittle);
    Memory findByContent(String content);
    Memory findByCategoryId(String categoryId);
    

}

    