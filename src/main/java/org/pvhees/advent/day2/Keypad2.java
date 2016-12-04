package org.pvhees.advent.day2;

public class Keypad2 {
    private static final int MAX_ROWCOL_KEYPAD = 5;
    private static final int MAX_ROWCOL_INDEX_KEYPAD = MAX_ROWCOL_KEYPAD - 1;

    private static final char[][] KEYPAD = new char[][]{
            {'-', '-', '1', '-', '-'},
            {'-', '2', '3', '4', '-'},
            {'5', '6', '7', '8', '9'},
            {'-', 'A', 'B', 'C', '-'},
            {'-', '-', 'D', '-', '-'}
    };

    public Keypad2() {
    }

    public String findCombinationKeypad(String[] instructions) {
        StringBuilder result = new StringBuilder();

        // startpunt in het KEYPAD is de '5'
        int x = 0;
        int y = 2;

        System.out.println("Start op " + KEYPAD[y][x]);

        int newX = 0;
        int newY = 0;

        for (String line : instructions) {
            for (char instruction : line.toCharArray()) {
                switch (instruction) {
                    case 'L':
                        newX = Math.max(x - 1, 0);
                        x = KEYPAD[y][newX] != '-' ? newX : x;
                        break;
                    case 'R':
                        newX = Math.min(x + 1, MAX_ROWCOL_INDEX_KEYPAD);
                        x = KEYPAD[y][newX] != '-' ? newX : x;
                        break;
                    case 'U':
                        newY = Math.max(y - 1, 0);
                        y = KEYPAD[newY][x] != '-' ? newY : y;
                        break;
                    case 'D':
                        newY = Math.min(y + 1, MAX_ROWCOL_INDEX_KEYPAD);
                        y = KEYPAD[newY][x] != '-' ? newY : y;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported instruction found " + instruction);
                }
            }
            System.out.println("Geland op " + KEYPAD[y][x]);

            result.append(KEYPAD[y][x]);
        }

        return result.toString();
    }

}
