package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentVoucherSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejected() {
        paymentData.put("voucherCode", "INVALID");
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferSuccess() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment("2", "BANK_TRANSFER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferRejected() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment("2", "BANK_TRANSFER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}
