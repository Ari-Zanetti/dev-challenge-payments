package dev.challenge.payments.bankgatewaysimulator;

import dev.challenge.payments.models.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentOutcomeSimulator {

	@Id
	private Long transactionId;

	private PaymentStatus paymentStatus;
}
