package com.caramelwaffle.accounts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerData",
        description = "Schema to hold Customer and Account information"
)
public class CustomerData {
    @Schema(
            description = "Name of the customer", example = "Waffle Bytes"
    )
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 5, max = 30, message = "Name must > 5 and <= 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@Wafflebytes.com"
    )
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email address is not valid")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountData accountData;
}
