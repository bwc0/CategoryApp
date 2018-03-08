package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO putUpdateVendor(Long id, VendorDTO vendorDTO);
    VendorDTO patchUpdateVendor(Long id, VendorDTO vendorDTO);
}
