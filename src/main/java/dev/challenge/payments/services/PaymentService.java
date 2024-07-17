package dev.challenge.payments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRepository;
import dev.challenge.payments.models.PaymentRequest;
import dev.challenge.payments.models.PaymentStatus;
import lombok.Getter;

@Getter
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public void insertPayment(PaymentRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setCardNumber(paymentRequest.getCardNumber());
		payment.setCardHolder(paymentRequest.getCardHolder());
		payment.setCardSecurityCode(new BCryptPasswordEncoder().encode(paymentRequest.getCardSecurityCode()));
		payment.setCardExpirationDate(paymentRequest.getCardExpirationDate());
		payment.setPaymentReference(paymentRequest.getPaymentReference());
		payment.setTransactionValue(paymentRequest.getTransactionValue());
		payment.setStatus(PaymentStatus.REQUESTED);

		getPaymentRepository().save(payment);
	}

	public List<Payment> readAllPayments() {
		return getPaymentRepository().findAll();
	}
}
