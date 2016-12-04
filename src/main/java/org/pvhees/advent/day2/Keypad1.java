package org.pvhees.advent.day2;

public class Keypad1 {
    private static final int MAX_ROWCOL_KEYPAD = 3;
    private static final int MAX_ROWCOL_INDEX_KEYPAD = MAX_ROWCOL_KEYPAD - 1;

    private static final char[][] KEYPAD = new char[][]{
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    public Keypad1() {
    }

    public String findCombinationKeypad(String[] instructions) {
        StringBuilder result = new StringBuilder();

        // startpunt in het KEYPAD is de '5'
        int x = 1;
        int y = 1;
        System.out.println("Start op " + KEYPAD[y][x]);

        for (String line : instructions) {
            for (char instruction : line.toCharArray()) {
                switch (instruction) {
                    case 'L':
                        x = Math.max(x - 1, 0);
                        break;
                    case 'R':
                        x = Math.min(x + 1, MAX_ROWCOL_INDEX_KEYPAD);
                        break;
                    case 'U':
                        y = Math.max(y - 1, 0);
                        break;
                    case 'D':
                        y = Math.min(y + 1, MAX_ROWCOL_INDEX_KEYPAD);
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
