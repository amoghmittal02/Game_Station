package Entity.MonstersAndHeroes;

import static Entity.Color.RED;

public class InaccessibleSpace extends Space{
    public InaccessibleSpace() {
        super("I", false, RED);
    }

    @Override
    public void interact(String type) {

    }
}
