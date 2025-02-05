package Rule;

import Entity.Board;
import Entity.ChessPiece;
import Entity.Player;
import Entity.Team;

import java.util.Scanner;
import java.util.zip.CheckedInputStream;

import static Entity.Color.RESET;
import static Entity.Color.YELLOW;
import static Rule.WinRule.*;

/**
 * normal game rule, can be reused
 */
public abstract class NormalRule {
    abstract int roleRule();

    /**
     *
     * @param role
     * @param teamNum
     * @param ifInOrder
     * @return
     */
    public Team[] teamRule(int role, int teamNum, boolean ifInOrder){
        Scanner scanner = new Scanner(System.in);
        System.out.println("do you want to play in team? input 'y' to agree, or anything else to disagree");
        String input = scanner.next();
        if (input.charAt(0) != 'y'){
            System.out.println();
            System.out.println(YELLOW + "Spawning prawn A for player 1 and Prawn B for Player 2..." + RESET);
            System.out.println();
            return null;
        }
        else {
            int num;
            String name;
            Team[] teams = new Team[teamNum];
            if (teamNum == 2 && !ifInOrder){
                teams[0] = new Team();
                teams[1] = new Team();

                System.out.println("input your team’s name:");
                name = scanner.nextLine();
                teams[role / 2].setName(name);
                System.out.println("input number of members:");
                while(true){
                    int number = InputRule.InputInteger(1, Integer.MAX_VALUE);
                    if (number != -1){
                        num = number;
                        break;
                    }
                }
                teams[role / 2].setNum(num);
                teams[role / 2].setRole(role);
                teams[role / 2].setPlayers(new Player[num]);

                if (role == 1){
                    role = 2;
                } else if (role == 2) {
                    role = 1;
                }
                System.out.println("input another team’s name:");
                name = scanner.nextLine();
                teams[role / 2].setName(name);
                System.out.println("input number of members:");
                while(true){
                    int number = InputRule.InputInteger(1, Integer.MAX_VALUE);
                    if (number != -1){
                        num = number;
                        break;
                    }
                }
                teams[role / 2].setNum(num);
                teams[role / 2].setRole(role);
                teams[role / 2].setPlayers(new Player[num]);
                if(teamNum == 4){
                    System.out.println();
                    System.out.println(YELLOW+"Spawning prawn A for " + teams[0].getName() + ", Prawn B for " + teams[1].getName() +", Prawn C for " + teams[2].getName()+ ", Prawn D for " + teams[3].getName() + "..." + RESET);
                    System.out.println();
                }
                if(teamNum==2){
                    System.out.println();
                    System.out.println(YELLOW + "Spawning prawn A for " + teams[0].getName() + " and Prawn B for " +  teams[1].getName()+ "..." + RESET);
                    System.out.println();
                }
            }
            else if(ifInOrder){
                for(int i = 0; i < teamNum;i++){
                    System.out.println("input team " + (i+1) + "'s name:");
                    name = scanner.next();
                    scanner.nextLine();
                    System.out.println("input number of members:");
                    while(true){
                        int number = InputRule.InputInteger(1, Integer.MAX_VALUE);
                        if (number != -1){
                            num = number;
                            break;
                        }
                    }
                    teams[i] = new Team(name, i+1, num);
                }
                if(teamNum == 4){
                    System.out.println();
                    System.out.println(YELLOW+"Spawning prawn A for " + teams[0].getName() + ", Prawn B for " + teams[1].getName() + ", Prawn C for " + teams[2].getName()+ ", Prawn D for " + teams[3].getName() + "..." + RESET);
                    System.out.println();
                }
                if(teamNum == 2){
                    System.out.println();
                    System.out.println(YELLOW + "Spawning prawn A for " + teams[0].getName() + " and Prawn B for " +  teams[1].getName()+ "..." + RESET);
                    System.out.println();
                }

            }

            return teams;
        }
    }

    /**
     * check whether the player is in the team
     * @param role
     * @param teams
     * @return
     */
    public int checkTeamMember(int role, Team[] teams) {
        int member;
        if(teams[role - 1].getNum() == 1){
            System.out.println();
            System.out.println("Only one member in team " + teams[role - 1].getName());
            member = 1;
            return member;
        }

        System.out.println("Choose player in team " + teams[role - 1].getName() + ". Char:(" + (char)('A' + role - 1) + ")");
        System.out.print("The team has " + teams[role - 1].getNum() + " members, select player number 1-"+teams[role - 1].getNum() +": ");

        while(true){
            member = InputRule.InputInteger(1, teams[role - 1].getNum());
            if (member == -1){
                continue;
            }
            else
                break;
        }
        return member;
    }

    /**
     * check whether the input is valid
     * @param role
     * @param board
     * @return updated board
     */
    public Board inputRule(int role, Board board){
        System.out.println("input a cell with row and column, split by a space. e.g.(1 1):");
        Integer[] numbers = InputRule.InputPiece(board);
        board.changeOnePiece(numbers[0], numbers[1], role);
        return board;
    }

    /**
     * check whether the game has a winner
     * @param board
     * @param winCount
     * @return
     */
    public int CheckWin(Board board, int winCount) {
        // check each row
        for (int row = 0; row < board.getRow(); row++) {
            int role = checkRow(board, row, winCount);
            if (role != -1) {
                return role;
            }
        }

        // check each column
        for (int col = 0; col < board.getCol(); col++) {
            int role = checkColumn(board, col, winCount);
            if (role != -1) {
                return role; // 返回该行符合条件的棋子的角色
            }
        }

        // check each diagonal
        // after checking the diagonal, if we still cannot get a winner, then we will return -1
        return checkAllDiagonals(board, winCount);

    }

    public boolean ifOutOfBound(int row, int col, Board board){
        boolean flag = !((row - 1 >= 0) && (row - 1 < board.getRow()) && (col - 1 >= 0) && (col - 1 < board.getCol()));
        return flag;
    }



}
