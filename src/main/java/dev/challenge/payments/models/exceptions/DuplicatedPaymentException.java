package dev.challenge.payments.models.exceptions;

public class DuplicatedPaymentException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicatedPaymentException(String paymentStatus) {
		super("The payment has already been requested with status: %s.".formatted(paymentStatus));
	}
}
