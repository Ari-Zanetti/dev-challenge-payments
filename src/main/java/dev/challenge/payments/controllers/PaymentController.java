package dev.challenge.payments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRequest;
import dev.challenge.payments.models.exceptions.DuplicatedPaymentException;
import dev.challenge.payments.services.BankGatewayService;
import dev.challenge.payments.services.PaymentService;
import lombok.Getter;

@Getter
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private BankGatewayService bankGatewayService;

	@PostMapping("/doPay")
	public ResponseEntity<String> doPay(@RequestBody PaymentRequest paymentRequest) {
		String message = "Payment requested successfully.";
		try {
			Payment payment = getPaymentService().insertPaymentOrThrow(paymentRequest);
			getBankGatewayService().requestPayment(payment);
		} catch (DuplicatedPaymentException ex) {
			message = ex.getMessage();
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/showPayments")
	public List<Payment> showPayments() {
		return getPaymentService().readAllPayments();
	}

}
