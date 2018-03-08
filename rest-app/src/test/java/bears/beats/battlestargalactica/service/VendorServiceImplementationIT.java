package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;
import bears.beats.battlestargalactica.api.v1.mapper.VendorMapper;
import bears.beats.battlestargalactica.bootstrapClasses.Bootstrap;
import bears.beats.battlestargalactica.domain.Vendor;
import bears.beats.battlestargalactica.repository.CategoryRepository;
import bears.beats.battlestargalactica.repository.CustomerRepository;
import bears.beats.battlestargalactica.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceImplementationIT {

    private final String updatedName = "New Name";

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    VendorService vendorService;

    @Before
    public void setUp() {

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository,
                vendorRepository);

        bootstrap.run();

        vendorService = new VendorServiceImplementation(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void patchVendorUpdateName() {
        long id = vendorRepository.findAll()
                .get(0)
                .getId();

        Vendor original = vendorRepository.getOne(id);
        assertNotNull(original);

        String originalName = original.getName();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(updatedName);

        vendorService.patchUpdateVendor(id, vendorDTO);

        Vendor updated = vendorRepository.findById(id).get();

        assertNotNull(updated);
        assertEquals(vendorDTO.getName(), updated.getName());
        assertThat(originalName, not(equalTo(updated.getName())));
    }
}
