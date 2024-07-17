package dev.challenge.payments.utils;

import java.util.List;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentStatus;

public class PaymentUtils {

	private PaymentUtils() {
	}

	public static boolean isStatusPending(Payment payment) {
		return List.of(PaymentStatus.REQUESTED, PaymentStatus.PENDING).contains(payment.getStatus());
	}
}
