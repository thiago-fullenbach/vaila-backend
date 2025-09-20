package br.com.thiago.vaila.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Base62ConversionUtilTest {
    @Test
    public void testEncodeDecode() {
        // Non negative values. IDs can't be negative
        long[] values = { 0, 1, 23, 154, Long.MAX_VALUE };
        for(Long value : values) {
            String encoded = Base62ConversionUtil.encode(value);
            System.out.println("Encoded = " + encoded + "\n");
            Long decoded = Base62ConversionUtil.decode(encoded);
            assertEquals(value, decoded, "Failed to encode/decode value: " + value);
        }
    }

    @Test
    public void testInvalidCharacterDecode() {
        assertThrows(IllegalArgumentException.class, () -> Base62ConversionUtil.decode("1B$"));
    }
}
