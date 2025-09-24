package br.com.thiago.vaila.util;

/**
 * A Base62 conversion component, used to create a short URL based on the URL id.
 */
public class Base62ConversionUtil {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = 62;

    private Base62ConversionUtil() {
        // Empty constructor, avoids initialization
    }

    /**
     * Method for encoding a long value to a Base62 String.
     * 
     * @param value the long, decimal value
     * @return A Base62 String
     */
    public static String encode(long value) {
        long tempValue = value;
        StringBuilder result = new StringBuilder();

        if(tempValue < BASE)
            return result.append(ALPHABET.charAt((int) tempValue)).toString();

        while(tempValue > 0) {
            result.append(ALPHABET.charAt((int) (tempValue % BASE)));
            tempValue /= BASE;
        }

        return result.reverse().toString();
    }

    /**
     * Method for decoding Base62 String. It returns a long representing the Base62 in decimal.
     * If the Base62 String is invalid, throws an IllegalArgumentException.
     * 
     * @param base62 a base62 in String format
     * @return A long value representing the Base62 String as a decimal
     * @throws IllegalArgumentException if the Base62 String is invalid
     */
    public static long decode(String base62) throws IllegalArgumentException {
        long result = 0;
        for(Character c : base62.toCharArray()) {
            int charIndex = ALPHABET.indexOf(c);
            if(charIndex == -1)
                throw new IllegalArgumentException("Invalid Base62 value");
            result = (result * BASE) + charIndex;
        }

        return result;
    }
}
