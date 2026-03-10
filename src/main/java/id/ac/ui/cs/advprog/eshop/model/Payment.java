package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {

    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = validateStatus();
    }

    private String validateStatus() {
        if (method.equals("VOUCHER")) {
            return validateVoucher();
        } else if (method.equals("BANK_TRANSFER")) {
            return validateBankTransfer();
        }
        return "REJECTED";
    }

    private String validateVoucher() {
        String code = paymentData.get("voucherCode");
        if (code == null || code.length() != 16 || !code.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int digitCount = 0;
        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return (digitCount == 8) ? "SUCCESS" : "REJECTED";
    }

    private String validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String refCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isBlank() || refCode == null || refCode.isBlank()) {
            return "REJECTED";
        }
        return "SUCCESS";
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
