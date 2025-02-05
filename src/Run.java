import Controller.BeginController;
import Rule.InputRule;

public class Run {
    public static void main(String[] args) {
        while(true){
            BeginController beginController = new BeginController();
            beginController.beginGame();  // begin the game
            System.out.println("Do you want to play again? input 'y' to continue");
            if(InputRule.InputChar('a','z') != 'y'){
                break;
            }
        }

    }
}