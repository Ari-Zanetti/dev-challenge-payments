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
import dev.challenge.payments.models.PaymentResponse;
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
	public ResponseEntity<PaymentResponse> doPay(@RequestBody PaymentRequest paymentRequest) {
		PaymentResponse response = new PaymentResponse();
		try {
			Payment payment = getPaymentService().insertPaymentOrThrow(paymentRequest);
			getBankGatewayService().requestPayment(payment);

			response.setTransactionId(payment.getTransactionId());
			response.setMessage("Payment requested successfully.");
		} catch (DuplicatedPaymentException ex) {
			response.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/showPayments")
	public ResponseEntity<List<Payment>> showPayments() {
		return new ResponseEntity<>(getPaymentService().readAllPayments(), HttpStatus.OK);
	}

}
