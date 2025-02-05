package Rule;

import Entity.Board;
import Interface.SpecificRule;

import java.util.Scanner;

import static Rule.WinRule.checkAllDiagonals;
import static Rule.WinRule.checkRow;

/**
 * rules of tic-tac-toe
 */
public class TTTRule extends NormalRule{
    /**
     * get the role from player
     * @return inputted role
     */
    @Override
    public int roleRule(){
        Scanner scanner = new Scanner(System.in);
        int role = -1;
        System.out.println("input role: 1 for x, 2 for o");
        while(true){
            role = InputRule.InputInteger(1, 2);
            if (role == -1) {
                continue;
            }
            else
                break;
        }
//            if (!BasicCheck.TimeCheck(times)){
//                exit(0);
//            }
        return role;
    }

}
