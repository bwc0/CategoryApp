package bears.beats.battlestargalactica.api.v1.mapper;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;
import bears.beats.battlestargalactica.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    private static final String NAME = "Vendor Store";
    private static final Long ID = 1L;
    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTOTest() {

        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendorTest() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}