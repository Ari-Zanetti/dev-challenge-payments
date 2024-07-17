package dev.challenge.payments.services;

import java.util.Random;

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

		// SEND PAYMENT REQUEST TO BANK GATEWAY

		if (rand.nextInt(10) > 5) // SIMULATE INSERT OK
			payment.setStatus(PaymentStatus.PENDING);
		else // SIMULATE REQUEST FAILED
			payment.setStatus(PaymentStatus.REQUEST_FAILED);

		getPaymentRepository().save(payment);
	}
}
