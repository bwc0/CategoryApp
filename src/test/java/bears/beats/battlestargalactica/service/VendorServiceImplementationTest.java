package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;
import bears.beats.battlestargalactica.api.v1.mapper.VendorMapper;
import bears.beats.battlestargalactica.controller.v1.VendorController;
import bears.beats.battlestargalactica.domain.Vendor;
import bears.beats.battlestargalactica.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplementationTest {

    private static final String NAME = "Vendor name";
    private VendorDTO vendorDTO;
    private VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendor_url(VendorController.BASEURL);

        vendorService = new VendorServiceImplementation(VendorMapper.INSTANCE,
                vendorRepository);
    }

    @Test
    public void getAllVendorsTest() {

        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();
        assertEquals(vendors.size(), vendorDTOS.size());
    }

    @Test
    public void getVendorByIdTest() {

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);

        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        VendorDTO vendorDTO = vendorService.getVendorById(vendor.getId());

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void createNewVendorTest() {

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(savedVendor.getName(), savedVendorDTO.getName());
        assertEquals(VendorController.BASEURL + "/1", savedVendorDTO.getVendor_url());
    }

    @Test
    public void putUpdateVendorTest() {

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO updatedVendor = vendorService.putUpdateVendor(1L, vendorDTO);

        assertEquals(savedVendor.getName(), updatedVendor.getName());
        assertEquals(VendorController.BASEURL + "/1", updatedVendor.getVendor_url());
    }

    @Test
    public void deleteVendorTest() {
        Long id = 1L;
        vendorService.deleteVendorById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}