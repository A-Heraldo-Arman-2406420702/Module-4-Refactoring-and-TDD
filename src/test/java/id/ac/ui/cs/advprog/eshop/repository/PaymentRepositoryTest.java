package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    Payment payment;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("1", "VOUCHER", paymentData);
    }

    @Test
    void testSave() {
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Payment findResult = paymentRepository.findById("non-existent");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment);
        assertEquals(1, paymentRepository.findAll().size());
    }
}
