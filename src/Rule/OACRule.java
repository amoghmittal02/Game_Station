package Rule;

import Entity.Board;
import Interface.SpecificRule;

import java.util.Scanner;

/** rule of order and and chaos
 */
public class OACRule extends NormalRule{

    /**
     * check the role input
     * @return inputted role(order or chaos)
     */
    @Override
    public int roleRule() {
        int role = -1;
        System.out.println("input role: 1 for order, 2 for chaos");
        while(true){
            role = InputRule.InputInteger(1, 2);
            if (role == -1) {
                continue;
            }
            else
                break;
        }
            return role;

    }

    /**
     *
     * @param type
     * @return round chosen role
     */
    public int chooseRole(int type) {
        int role = -1;
        if (type == 1) {
            System.out.println("you are the order");
        }else if(type == 2){
            System.out.println("you are the chaos");
        }
        System.out.println("please input your type: 1 for x, 2 for o");
        while(true){
            role = InputRule.InputInteger(1, 2);
            if (role == -1) {
                continue;
            }
            else
                break;
        }
        return role;

    }
}
