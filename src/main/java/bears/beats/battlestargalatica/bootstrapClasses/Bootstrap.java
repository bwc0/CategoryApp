package bears.beats.battlestargalatica.bootstrapClasses;

import bears.beats.battlestargalatica.domain.Category;
import bears.beats.battlestargalatica.domain.Customer;
import bears.beats.battlestargalatica.repository.CategoryRepository;
import bears.beats.battlestargalatica.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.saveAll(loadCategory());
        System.out.println("Category loaded " + categoryRepository.count());

        customerRepository.saveAll(loadCustomers());
        System.out.println("Customer loaded " + categoryRepository.count());
    }

    private List<Customer> loadCustomers() {
        Customer dexter = new Customer();
        dexter.setFirstName("Dexter");
        dexter.setLastName("Morgan");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Scott");

        Customer dave = new Customer();
        dave.setFirstName("Dave");
        dave.setLastName("Chapelle");

        Customer rock = new Customer();
        rock.setFirstName("Dwayne");
        rock.setLastName("Johnson");

        Customer kevin = new Customer();
        kevin.setFirstName("Kevin");
        kevin.setLastName("Hart");

        return Arrays.asList(dexter, michael, dave, rock, kevin);
    }

    private List<Category> loadCategory() {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        return  Arrays.asList(fruits, dried, fresh, exotic, nuts);
    }
}
