package dev.challenge.payments.bankgatewaysimulator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOutcomeSimulatorRepository extends JpaRepository<PaymentOutcomeSimulator, Long> {

	public PaymentOutcomeSimulator findFirstByTransactionId(Long transactionId);

}
