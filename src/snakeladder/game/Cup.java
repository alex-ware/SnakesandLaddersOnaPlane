package snakeladder.game;

import java.util.List;

import java.util.ArrayList;

// cup aims to create and collect all dice value rolled; if the dice is rolled, it will return the respective index
public class Cup {
    private List<Die> dice;
    private NavigationPane np;
    private int totalRolled;


    public Cup(NavigationPane np) {
        this.np = np;
        this.dice = new ArrayList<>();
        this.totalRolled = 0;
    }

    public void roll(int nb) {
        int size = dice.size();
        Die die = new Die(nb, this, size + 1);
        dice.add(die);
        totalRolled += nb;
    }

    public void finishedRolling(int index) {
        if (index == np.getNumberOfDice()) {
            np.startMoving(totalRolled);
            resetCup();
        }
    }
    public List<Die> getAllDice() {
        return this.dice;
    }

    // resets the cup class so that the index can be used again
    private void resetCup() {
        this.totalRolled = 0;
        this.dice.clear();
    }
}
