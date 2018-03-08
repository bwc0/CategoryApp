package bears.beats.battlestargalactica.repository;

import bears.beats.battlestargalactica.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
