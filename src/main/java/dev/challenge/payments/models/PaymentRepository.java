package dev.challenge.payments.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	public List<Payment> findByCardNumberAndTransactionValueAndPaymentReference(String cardNumber, double transactionValue, String paymentReference);

}
