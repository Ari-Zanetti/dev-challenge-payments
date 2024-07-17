package dev.challenge.payments.services;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRepository;
import dev.challenge.payments.models.PaymentStatus;
import lombok.Getter;

@Getter
@Service
public class BankGatewayService {

	@Autowired
	private PaymentRepository paymentRepository;

	private Random rand = new Random(12345);

	public void requestPayment(Payment payment) {

		// SIMULATE REQUEST PAYMENT TO BANK GATEWAY
		boolean isBankGatewayRequestOk = rand.nextBoolean();
		Logger.getLogger(BankGatewayService.class.getName()).info("Requested payment with id: %s".formatted(payment.getTransactionId()));

		if (isBankGatewayRequestOk) { // SIMULATE INSERT OK
			payment.setStatus(PaymentStatus.PENDING);
		} else { // SIMULATE INSERT OK
			payment.setStatus(PaymentStatus.REQUEST_FAILED);
		}

		getPaymentRepository().save(payment);
	}
}
