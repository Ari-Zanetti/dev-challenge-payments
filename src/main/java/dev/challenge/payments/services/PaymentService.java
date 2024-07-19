package dev.challenge.payments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentRepository;
import dev.challenge.payments.models.PaymentRequest;
import dev.challenge.payments.models.PaymentStatus;
import dev.challenge.payments.models.exceptions.DuplicatedPaymentException;
import dev.challenge.payments.utils.PaymentUtils;
import lombok.Getter;

@Getter
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment insertPayment(PaymentRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setCardNumber(paymentRequest.getCardNumber());
		payment.setCardHolder(paymentRequest.getCardHolder());
		payment.setCardSecurityCode(new BCryptPasswordEncoder().encode(paymentRequest.getCardSecurityCode()));
		payment.setCardExpirationDate(paymentRequest.getCardExpirationDate());
		payment.setPaymentReference(paymentRequest.getPaymentReference());
		payment.setTransactionValue(paymentRequest.getTransactionValue());
		payment.setStatus(PaymentStatus.REQUESTED);

		return getPaymentRepository().save(payment);
	}

	public Payment insertPaymentOrThrow(PaymentRequest paymentRequest) throws DuplicatedPaymentException {
		List<Payment> payments = getPaymentRepository().findByCardNumberAndTransactionValueAndPaymentReference(paymentRequest.getCardNumber(), paymentRequest.getTransactionValue(), paymentRequest.getPaymentReference());
		for (Payment payment : payments)
			if (payment != null && !PaymentUtils.isStatusRejected(payment))
				throw new DuplicatedPaymentException(payment.getStatus().name());

		return insertPayment(paymentRequest);
	}

	public Payment updatePayment(Payment payment) {
		return getPaymentRepository().save(payment);
	}

	public List<Payment> readAllPayments() {
		return getPaymentRepository().findAll();
	}

	public List<Payment> readAllPaymentsPending() {
		return getPaymentRepository().findByStatusIn(PaymentUtils.getPendingPaymentStatusList());
	}
}
