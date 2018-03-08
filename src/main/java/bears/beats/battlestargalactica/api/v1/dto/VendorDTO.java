package bears.beats.battlestargalactica.api.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    @ApiModelProperty(value = "name of the vendor", required = true)
    private String name;
    @ApiModelProperty(value = "vendor endpoint", required = true)
    private String vendor_url;
}
