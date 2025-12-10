
package Group2.MyMemory.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Group2.MyMemory.entity.Memory;
import java.util.List;


@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
   List<Memory> findByUserId(Long userId);
    

}

    