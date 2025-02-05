package Rule;

import Controller.SuperTTTController;
import Entity.Board;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Entity.Color.*;

/**
 * check players' input
 */
public class InputRule {
    /**
     * Input an integer in range [min, max]
     * @param min
     * @param max
     * @return input number
     */
    public static int InputInteger(int min, int max){
        Scanner scanner = new Scanner(System.in);
        int number;

        String input = scanner.nextLine();

        // check input
        if (input.matches("-?\\d+")) {
            number = Integer.parseInt(input);
            if (number >= min && number <= max) {
//                scanner.nextLine();
                return number;
            } else {
//                System.out.println(YELLOW+"input out of range, please input again"+RESET);
//                scanner.nextLine();
            }
        } else {
//            System.out.println(YELLOW+"invalid input, please input again"+RESET);
//            scanner.nextLine();
        }
        return -1;
    }

    /**
     * Input a char in range [min, max]
     * @param min
     * @param max
     * @return input char
     */
    public static char InputChar(char min, char max){
        Scanner scanner = new Scanner(System.in);
        char inputChar;

        String input = scanner.next();
        if (input.length() == 1) {
            inputChar = input.charAt(0);
            if (inputChar >= min && inputChar <= max) {
                return inputChar;
            } else {
//                System.out.println("Invalid input, you should input a character between " + min + " and " + max + ", please input again");
                return '\0';
            }
        } else {
//            System.out.println("Invalid input, you should input a single character, please input again");
            return '\0';
        }
    }

    /**
     * Input a piece in range [1, row] [1, column]
     * @param board
     * @return input piece
     */
    public static Integer[] InputPiece(Board board){
        int row = board.getRow();
        int column = board.getCol();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();

            String regex = "^\\d+\\s+\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                String[] numbers = input.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                if (num1 > 0 && num1 <= row && num2 > 0 && num2 <= column) {
                    if(!board.checkCellFull(num1, num2)){
                        return new Integer[]{num1, num2};
                    }else{
                        System.out.println("The cell is already full, please input again");
                    }

                } else {
                    System.out.println("The input is out of range, please input again");
                }
            } else {
                System.out.println("The input format is incorrect. You should enter two integers separated by Spaces, please input again");
            }
        }
    }

    public static Integer[] InputPieceOutOfRange(Board board){
        int row = board.getRow();
        int column = board.getCol();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();

            String regex = "^\\d+\\s+\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                String[] numbers = input.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                if (num1 > 0 && num1 <= row && num2 > 0 && num2 <= column) {
                    return new Integer[]{num1, num2};
                } else {
                    System.out.println("The input is out of range, please input again");
                }
            } else {
                System.out.println("The input format is incorrect. You should enter two integers separated by Spaces, please input again");
            }
        }
    }

    /**
     * Input a piece in range [X,row column]
     * @param board
     * @return index of small board, row and col
     */
    public static Integer[] SuperInputPiece(Board board) {
        int row = board.getRow();
        int column = board.getCol();
        Scanner scanner = new Scanner(System.in);

        int[][] offsets = SuperTTTController.getOffsets();

        while (true) {
            String input = scanner.nextLine();

            // check input
            String regex = "^[A-I],\\d+\\s+\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                // split input
                String[] parts = input.split(",");
                String letterPart = parts[0];
                String numberPart = parts[1];

                // letter to index
                char letter = letterPart.charAt(0);
                int letterIndex = letter - 'A';

                // analyse number
                String[] numbers = numberPart.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);

                // get offset
                int rowOffset = offsets[letterIndex][0];
                int colOffset = offsets[letterIndex][1];

                // get global place
                int globalRow = num1 + rowOffset;
                int globalCol = num2 + colOffset;

                if (globalRow > 0 && globalRow <= row && globalCol > 0 && globalCol <= column) {
                    if (!board.checkCellFull(globalRow - 1, globalCol - 1)) {
                        return new Integer[]{letterIndex, globalRow, globalCol};
                    } else {
                        System.out.println("The cell is already full, please input again");
                    }
                } else {
                    System.out.println("The input is out of range, please input again");
                }
            } else {
                System.out.println("The input format is incorrect. You should enter in the format 'A,1 1'. The letter should be A-I, row and column should be 1-9. Please input again.");
            }
        }
    }

    /**
     * Input a fence
     * @param board
     * @return
     */
    public static Integer[] InputFence(Board board) {
        int row = board.getRow();
        int column = board.getCol();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();

            String regex = "^\\d+\\s+\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                String[] numbers = input.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                if((num1 == 1 && num2 == 1) || (num1 == 1 && num2 == 9) || (num1 == 9 && num2 == 9) || (num1 == 9 && num2 == 1)){
                    System.out.println("You can't place a fence on the corner, please input again");
                    continue;
                }
                if (num1 > 0 && num1 <= row + 1 && num2 > 0 && num2 <= column + 1) {
                    if(!board.checkIntervalFenceFull(num1, num2)){
                        return new Integer[]{num1, num2};
                    }else{
                        System.out.println("The interval is already full, please input again");
                    }

                } else {
                    System.out.println("The input is out of range, please input again");
                }
            } else {
                System.out.println("The input format is incorrect. You should enter two integers separated by Spaces, please input again");
            }
        }

    }

    /**
     * MAO choose an action
     * @param validInput
     * @return
     */
    public static char ChooseActionByCharacter(char[] validInput){
        Scanner scanner = new Scanner(System.in);
        char inputChar;
        String input = scanner.next();
        if (input.length() == 1) {
            inputChar = input.charAt(0);
            if (isValidInput(inputChar, validInput)) {
                return inputChar;
            } else {
//                System.out.println(YELLOW+"Invalid input, you should input a valid character the game supports, please input again"+RESET);
                return ' ';
            }
        } else {
//            System.out.println(YELLOW+"Invalid input, you should input a single character, please input again"+RESET);
            return ' ';
        }
    }

    /**
     * check if the input is valid
     * @param inputChar
     * @param validInput
     * @return
     */
    public static boolean isValidInput(char inputChar, char[] validInput) {
        for (char validChar : validInput) {
            if (inputChar == validChar) {
                return true;
            }
        }
        return false;
    }

    /**
     * Input hero index
     * @param max
     * @param num
     * @return
     */
    public static List<Integer> InputHeroIndex(int max, int num) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a series of space-separated integers:");

        String input = scanner.nextLine();
        String[] inputArray = input.split("\\s+");

        if (inputArray.length != num) {
            System.out.println(YELLOW+"The total number of integers is not " + num+RESET);
            return null;
        }

        List<Integer> numbers = new ArrayList<>();
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (int i = 0; i < inputArray.length; i++) {
            try {
                int number = Integer.parseInt(inputArray[i]);

                if (!uniqueNumbers.add(number)) {
                    System.out.println(YELLOW+"Duplicate number found at index " + i + ": " + number+RESET);
                    return null;
                }

                if (number < 1 || number > max) {
                    System.out.println(YELLOW+"The number at index " + i + " is out of range (should be between 1 and " + max + ")"+RESET);
                    return null;
                }

                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println(YELLOW+"Invalid input at index " + i + ", not a valid integer."+RESET);
                return null;
            }
        }

        return numbers;
    }

    public static ArrayList<Integer> InputNumbers(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a series of space-separated integers:");
        String input = scanner.nextLine();
        String[] inputArray = input.split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < inputArray.length; i++) {
            try {
                int number = Integer.parseInt(inputArray[i]);

                if (!uniqueNumbers.add(number)) {
                    System.out.println(YELLOW+"Duplicate number found at index " + i + ": " + number+RESET);
                    return null;
                }

                if (number < min || number > max) {
                    System.out.println(YELLOW+"The number at index " + i + " is out of range (should be between 1 and " + max + ")"+RESET);
                    return null;
                }

                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println(YELLOW+"Invalid input at index " + i + ", not a valid integer."+RESET);
                return null;
            }
        }

        return numbers;
    }

    public static Integer[] InputSpace(Board board){
        int row = board.getRow();
        int column = board.getCol();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();

            String regex = "^\\d+\\s+\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                String[] numbers = input.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                if (num1 > 0 && num1 <= row && num2 > 0 && num2 <= column) {
                    return new Integer[]{num1, num2};
                } else {
                    System.out.println("The input is out of range, please input again");
                }
            } else {
                System.out.println("The input format is incorrect. You should enter two integers separated by Spaces, please input again");
            }
        }
    }
}
