public class BinaryTranslator {
    public static String textToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            String bin = Integer.toBinaryString(c);
            binary.append(String.format("%8s", bin).replaceAll(" ", "0")); // Форматируем до 8 бит
            binary.append(" ");
        }
        return binary.toString().trim();
    }
}
