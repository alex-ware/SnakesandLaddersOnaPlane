package snakeladder.game;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    String playerName;

    Map<Integer, Integer> rolledMap = new HashMap<>();

    int travelledUp, travelledDown;

    public Statistics(String playerName) {
        this.playerName = playerName;
    }

    public void rolled(int nb) {
        if (rolledMap.containsKey(nb)) {
            rolledMap.put(nb, rolledMap.get(nb) + 1);
        } else {
            rolledMap.put(nb, 1);
        }
    }

    public void up() {
        travelledUp++;
    }

    public void down() {
        travelledDown++;
    }

    @Override
    public String toString() {
        String stats = String.format("%s rolled: %s, up=%d, down=%d",playerName, rolledMap.entrySet().toString(),travelledUp,travelledDown);
        return stats;
    }
}
