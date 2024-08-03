package com.caramelwaffle.card.entity;

import com.caramelwaffle.card.model.CardData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "card")
public class CardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private Long cardId;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

    public CardData toData(CardData entry){
        entry.setCardId(this.cardId);
        entry.setCardNumber(this.cardNumber);
        entry.setCardType(this.cardType);
        entry.setAmountUsed(this.amountUsed);
        entry.setAvailableAmount(this.availableAmount);
        entry.setMobileNumber(this.mobileNumber);
        entry.setTotalLimit(this.totalLimit);
        return entry;
    }
}
