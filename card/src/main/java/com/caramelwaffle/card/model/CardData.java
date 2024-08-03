package com.caramelwaffle.card.model;

import com.caramelwaffle.card.entity.CardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "CardData",
        description = "Schema to hold card information"
)
public class CardData {
    private Long cardId;

    @Schema(description = "Mobile Number of the customer", example = "9345432123")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Card Number of the customer")
    @Pattern(regexp="(^$|\\d{12})",message = "CardNumber must be 12 digits")
    @NotEmpty
    private String cardNumber;

    @Schema(description = "Type of the card")
    @NotEmpty(message = "CardType can not be a null or empty")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a Customer", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;

    public CardEntity toEntity(CardEntity entity) {
        entity.setMobileNumber(this.mobileNumber);
        entity.setCardNumber(this.cardNumber);
        entity.setCardType(this.cardType);
        entity.setTotalLimit(this.totalLimit);
        entity.setAmountUsed(this.amountUsed);
        entity.setAvailableAmount(this.availableAmount);
        return entity;
    }
}