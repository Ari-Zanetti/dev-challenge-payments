package dev.challenge.payments.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	private String cardNumber;

	private String cardHolder;

	private String cardSecurityCode;

	private Date cardExpirationDate;

	private double transactionValue;

	private String paymentReference;

	private PaymentStatus status;

}
