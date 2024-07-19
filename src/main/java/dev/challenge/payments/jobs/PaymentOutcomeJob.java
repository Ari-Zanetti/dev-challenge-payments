package dev.challenge.payments.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dev.challenge.payments.bankgatewaysimulator.BankGatewaySimulatorService;
import dev.challenge.payments.bankgatewaysimulator.PaymentOutcomeSimulator;
import dev.challenge.payments.models.Payment;
import dev.challenge.payments.services.PaymentService;
import lombok.Getter;

@Getter
@Service
public class PaymentOutcomeJob {

	@Autowired
	private BankGatewaySimulatorService paymentOutcomeSimulatorService;

	@Autowired
	private PaymentService paymentService;

	@Scheduled(cron = "${jobs.PaymentOutcomJob.cron}")
	public void checkPaymentOutcomesUpdated() {
		List<Payment> pendingPayments = getPaymentService().readAllPaymentsPending();
		for (Payment payment : pendingPayments) {
			PaymentOutcomeSimulator paymentOutcome = getPaymentOutcomeSimulatorService().findFirstByTransactionId(payment.getTransactionId());
			if (paymentOutcome != null)
				payment.setStatus(paymentOutcome.getPaymentStatus());
			getPaymentService().updatePayment(payment);
		}
	}
}
