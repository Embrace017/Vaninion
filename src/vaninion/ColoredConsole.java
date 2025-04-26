package vaninion;

public class ColoredConsole {
    public static final String RESET = "\u001B[0m";

    // Basic Text Colors (Foreground)
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright Text Colors (Foreground)
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Grey Shades
    public static final String GREY = "\u001B[38;5;245m";
    public static final String DARK_GREY = "\u001B[38;5;240m";
    public static final String LIGHT_GREY = "\u001B[38;5;250m";

    // Basic Background Colors
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    // Bright Background Colors
    public static final String BRIGHT_BLACK_BACKGROUND = "\u001B[100m";
    public static final String BRIGHT_RED_BACKGROUND = "\u001B[101m";
    public static final String BRIGHT_GREEN_BACKGROUND = "\u001B[102m";
    public static final String BRIGHT_YELLOW_BACKGROUND = "\u001B[103m";
    public static final String BRIGHT_BLUE_BACKGROUND = "\u001B[104m";
    public static final String BRIGHT_PURPLE_BACKGROUND = "\u001B[105m";
    public static final String BRIGHT_CYAN_BACKGROUND = "\u001B[106m";
    public static final String BRIGHT_WHITE_BACKGROUND = "\u001B[107m";

    // Text Styles
    public static final String BOLD = "\u001B[1m";
    public static final String FAINT = "\u001B[2m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK_SLOW = "\u001B[5m";
    public static final String BLINK_FAST = "\u001B[6m";
    public static final String REVERSE = "\u001B[7m";
    public static final String CONCEALED = "\u001B[8m";
    public static final String CROSSED_OUT = "\u001B[9m";

    // Extended Text Colors (Foreground) - Example (you'll likely use the direct escape code in your output)
    // public static final String FG_256_COLOR = "\u001B[38;5;<n>m"; // Replace <n> with the color number (0-255)

    // Extended Background Colors - Example (you'll likely use the direct escape code in your output)
    // public static final String BG_256_COLOR = "\u001B[48;5;<n>m"; // Replace <n> with the color number (0-255)

    // True Color (24-bit RGB) Foreground - Example (you'll likely use the direct escape code in your output)
    // public static final String FG_TRUE_COLOR = "\u001B[38;2;<r>;<g>;<b>m"; // Replace <r>, <g>, <b> with RGB values (0-255)

    // True Color (24-bit RGB) Background - Example (you'll likely use the direct escape code in your output)
    // public static final String BG_TRUE_COLOR = "\u001B[48;2;<r>;<g>;<b>m"; // Replace <r>, <g>, <b> with RGB values (0-255)

    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return result.toString().trim();
    }
}