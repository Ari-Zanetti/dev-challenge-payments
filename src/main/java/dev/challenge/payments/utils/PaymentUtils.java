package dev.challenge.payments.utils;

import java.util.List;

import dev.challenge.payments.models.Payment;
import dev.challenge.payments.models.PaymentStatus;

public class PaymentUtils {

	private PaymentUtils() {
	}

	public static boolean isStatusRejected(Payment payment) {
		return List.of(PaymentStatus.REQUEST_FAILED, PaymentStatus.REFUSED).contains(payment.getStatus());
	}

	public static List<PaymentStatus> getPendingPaymentStatusList() {
		return List.of(PaymentStatus.PENDING);
	}
}
