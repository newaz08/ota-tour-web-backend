package com.technonext.ota.b2c.tour.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CommonUtil {

    private final SecureRandom random;

    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
    }

    public String generateUniqueNumber(String prefix, long serialNumber, long length) {
        String serialString = String.valueOf(serialNumber);
        return prefix +
            LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")) +
            random.ints('0', '9' + 1)
                .limit(length - serialString.length())
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) +
            serialNumber;
    }
}
