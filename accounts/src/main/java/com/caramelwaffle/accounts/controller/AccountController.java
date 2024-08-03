package com.caramelwaffle.accounts.controller;

import com.caramelwaffle.accounts.constants.AccountsConstants;
import com.caramelwaffle.accounts.model.AccountContactInfoData;
import com.caramelwaffle.accounts.model.CustomerData;
import com.caramelwaffle.accounts.model.ErrorResponseData;
import com.caramelwaffle.accounts.model.ResponseData;
import com.caramelwaffle.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts in WaffleBank",
        description = "CRUD REST APIs in WaffleBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountController {

    private final IAccountService iAccountService;

    public AccountController(IAccountService iAccountService){
        this.iAccountService = iAccountService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountContactInfoData accountContactInfoData;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside WaffleBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseData.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseData> createAccount(@Valid @RequestBody CustomerData customerData) {
        iAccountService.createAccount(customerData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseData.class)
                    )
            )
    })
    @GetMapping("/fetchAccountDetail")
    public ResponseEntity<CustomerData> fetchAccountDetail(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        CustomerData customerDataResponseEntity = iAccountService.fetchAccountDetail(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDataResponseEntity);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseData.class)
                    )
            )
    })
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseData> updateAccount(@Valid @RequestBody CustomerData customerData) {
        boolean isUpdateSuccess = iAccountService.updateAccount(customerData);
        if (isUpdateSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseData.class)
                    )
            )
    })
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseData> deleteAccount(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        boolean isDeleteSuccess = iAccountService.deleteAccount(mobileNumber);
        if (isDeleteSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoData> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountContactInfoData);
    }
}
