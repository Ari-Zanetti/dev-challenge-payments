package dev.challenge.payments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRequest;
import dev.challenge.payments.services.PaymentService;
import lombok.Getter;

@Getter
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/doPay")
	public HttpStatus doPay(@RequestBody PaymentRequest payment) {
		getPaymentService().insertPayment(payment);
		return HttpStatus.OK;
	}

	@GetMapping("/showPayments")
	public List<Payment> showPayments() {
		return getPaymentService().readAllPayments();
	}

}
