package bears.beats.battlestargalactica.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
