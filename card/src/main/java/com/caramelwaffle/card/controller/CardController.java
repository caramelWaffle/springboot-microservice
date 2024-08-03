package com.caramelwaffle.card.controller;

import com.caramelwaffle.card.model.CardData;
import com.caramelwaffle.card.model.ResponseData;
import com.caramelwaffle.card.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Card in WaffleBank",
        description = "CRUD REST APIs in WaffleBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
public class CardController {

    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createCard(@Valid @RequestBody CardData cardData) {
        cardService.createCard(cardData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData(
                        String.valueOf(HttpStatus.CREATED.value()),
                        HttpStatus.CREATED.getReasonPhrase()
                ));
    }

    @GetMapping("/find")
    public ResponseEntity<CardData> find(@RequestParam Long id) {
        CardData cardData = cardService.findCard(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardData);
    }

    @GetMapping("/findByMobileNumber")
    public ResponseEntity<CardData> findByMobileNumber(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        CardData cardData = cardService.findCardByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> update(@Valid @RequestBody CardData cardData) {
        boolean isSuccess = cardService.updateCard(cardData);
        if (isSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()),
                            HttpStatus.EXPECTATION_FAILED.getReasonPhrase()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam Long cardId) {
        boolean isSuccess = cardService.deleteCard(cardId);
        if (isSuccess) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseData(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseData(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()),
                            HttpStatus.EXPECTATION_FAILED.getReasonPhrase()));
        }
    }
}
