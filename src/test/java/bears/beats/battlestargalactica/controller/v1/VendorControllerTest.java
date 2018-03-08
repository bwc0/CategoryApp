package bears.beats.battlestargalactica.controller.v1;

import bears.beats.battlestargalactica.api.v1.dto.VendorDTO;
import bears.beats.battlestargalactica.controller.RestResponseEntityExceptionHandler;
import bears.beats.battlestargalactica.service.ResourceNotFoundException;
import bears.beats.battlestargalactica.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static bears.beats.battlestargalactica.controller.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    private static final String NAME = "Vendor Store";
    private VendorDTO vendorDTO;
    private VendorDTO returnDTO;
    private MockMvc mockMvc;

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendor_url(VendorController.BASEURL + "/1");

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendorsTest() throws Exception {
        List<VendorDTO> vendorDTOS = Arrays.asList(vendorDTO, new VendorDTO());

        when(vendorService.getAllVendors()).thenReturn(vendorDTOS);

        mockMvc.perform(get(VendorController.BASEURL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorByIdTest() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(VendorController.BASEURL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void getVendorByIdNotFoundTest() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASEURL + "/234")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewVendorTest() throws Exception {

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(VendorController.BASEURL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASEURL + "/1")));

    }

    @Test
    public void updateCustomerTest() throws Exception{

        when(vendorService.putUpdateVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(VendorController.BASEURL + "/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASEURL + "/1")));
    }

    @Test
    public void patchUpdateVendorTest() throws Exception {
        returnDTO.setName("New Vendor");

        when(vendorService.patchUpdateVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VendorController.BASEURL + "/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("New Vendor")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASEURL + "/1")));
    }

    @Test
    public void deleteVendorById() throws Exception {

        mockMvc.perform(delete(VendorController.BASEURL + "/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }
}