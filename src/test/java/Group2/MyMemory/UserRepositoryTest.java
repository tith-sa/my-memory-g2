package Group2.MyMemory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.UserRepositery;



public class UserRepositoryTest {
	@Autowired
	private UserRepositery repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
		
		
	}
	

}
