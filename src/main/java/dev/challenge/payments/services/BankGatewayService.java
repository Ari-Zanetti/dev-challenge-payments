package dev.challenge.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.challenge.payments.bankgatewaysimulator.BankGatewaySimulatorService;
import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRepository;
import dev.challenge.payments.models.PaymentStatus;
import lombok.Getter;

@Getter
@Service
public class BankGatewayService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BankGatewaySimulatorService bankGatewaySimulatorService;

	public Payment requestPayment(Payment payment) {
		boolean isBankGatewayRequestOk = getBankGatewaySimulatorService().requestPayment(payment);
		if (isBankGatewayRequestOk)
			payment.setStatus(PaymentStatus.PENDING);
		else
			payment.setStatus(PaymentStatus.REQUEST_FAILED);

		return getPaymentRepository().save(payment);
	}
}
