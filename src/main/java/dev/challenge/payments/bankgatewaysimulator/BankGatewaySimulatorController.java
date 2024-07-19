package dev.challenge.payments.bankgatewaysimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.challenge.payments.models.PaymentStatus;
import lombok.Getter;

@Getter
@RestController
public class BankGatewaySimulatorController {

	@Autowired
	private BankGatewaySimulatorService paymentOutcomeSimulatorService;

	@PostMapping("/doAcceptPayment")
	public ResponseEntity<String> doAcceptPayment(@RequestBody Long transactionId) {
		getPaymentOutcomeSimulatorService().updatePaymentStatus(transactionId, PaymentStatus.CONFIRMED);
		return new ResponseEntity<>("Payment completed.", HttpStatus.OK);
	}

	@PostMapping("/doRefusePayment")
	public ResponseEntity<String> doRefusePayment(@RequestBody Long transactionId) {
		getPaymentOutcomeSimulatorService().updatePaymentStatus(transactionId, PaymentStatus.REFUSED);
		return new ResponseEntity<>("Payment refused.", HttpStatus.OK);
	}

}
