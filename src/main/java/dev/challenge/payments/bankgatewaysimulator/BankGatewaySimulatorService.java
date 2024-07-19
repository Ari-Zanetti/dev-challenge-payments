package dev.challenge.payments.bankgatewaysimulator;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentStatus;
import dev.challenge.payments.services.BankGatewayService;
import lombok.Getter;

@Getter
@Service
public class BankGatewaySimulatorService {

	private Random rand = new Random(12345);

	@Autowired
	private PaymentOutcomeSimulatorRepository paymentOutcomeSimulatorRepository;

	public boolean requestPayment(Payment payment) {
		boolean isBankGatewayRequestOk = rand.nextBoolean();
		Logger.getLogger(BankGatewayService.class.getName()).info("Requested payment with id: %s".formatted(payment.getTransactionId()));
		PaymentOutcomeSimulator paymentOutcome = new PaymentOutcomeSimulator(payment.getTransactionId(), PaymentStatus.PENDING);
		getPaymentOutcomeSimulatorRepository().save(paymentOutcome);

		return isBankGatewayRequestOk;
	}

	public void updatePaymentStatus(Long transactionId, PaymentStatus paymentStatus) {
		PaymentOutcomeSimulator paymentOutcome = new PaymentOutcomeSimulator(transactionId, paymentStatus);
		getPaymentOutcomeSimulatorRepository().save(paymentOutcome);
	}

	public PaymentOutcomeSimulator findFirstByTransactionId(Long transactionId) {
		return getPaymentOutcomeSimulatorRepository().findFirstByTransactionId(transactionId);
	}
}
