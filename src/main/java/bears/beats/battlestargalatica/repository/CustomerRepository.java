package bears.beats.battlestargalatica.repository;

import bears.beats.battlestargalatica.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
