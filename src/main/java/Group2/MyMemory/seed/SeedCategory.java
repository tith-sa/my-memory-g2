package Group2.MyMemory.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// Assuming you have a Category entity defined elsewhere
import Group2.MyMemory.entity.Category; 
import Group2.MyMemory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeedCategory { 
    
    private final CategoryRepository categoryRepository;


    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Check if data already exists to prevent duplicates on every startup
            if (categoryRepository.count() == 0) {
                System.out.println("Seeding initial categories...");

                Category category1 = new Category();
                category1.setName("Work");
                category1.setDescription("Tasks related to work and professional life");
                categoryRepository.save(category1);

                Category category2 = new Category();
                category2.setName("Personal");
                category2.setDescription("Personal tasks and reminders");
                categoryRepository.save(category2);

                Category category3 = new Category();
                category3.setName("Shopping");
                category3.setDescription("Shopping lists and related tasks");
                categoryRepository.save(category3);

                Category category4 = new Category();
                category4.setName("Fitness");
                category4.setDescription("Health and fitness related tasks");
                categoryRepository.save(category4);
                
                System.out.println("Categories seeded successfully.");

            } else {
                System.out.println("Categories already seeded. Skipping initialization.");
            }
        };
    }
}
