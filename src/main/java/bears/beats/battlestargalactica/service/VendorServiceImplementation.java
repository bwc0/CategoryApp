package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;
import bears.beats.battlestargalactica.api.v1.mapper.VendorMapper;
import bears.beats.battlestargalactica.controller.v1.VendorController;
import bears.beats.battlestargalactica.domain.Vendor;
import bears.beats.battlestargalactica.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImplementation implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImplementation(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendor_url(VendorController.BASEURL + "/" + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO putUpdateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchUpdateVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {

                    if(vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO returnDTO = vendorMapper
                            .vendorToVendorDTO(vendorRepository.save(vendor));


                    returnDTO.setVendor_url(getVendorUrl(id));

                    return returnDTO;

                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDTO.setVendor_url(getVendorUrl(savedVendor.getId()));

        return returnDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASEURL + "/" + id;
    }
}
