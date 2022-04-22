package snakeladder.game;

import javax.xml.stream.Location;

public class SimpleStrategy implements Strategy{

    GamePane gp;
    public SimpleStrategy(GamePane gp){
        this.gp=gp;
    }

    @Override
    public void doStrategy(int maxDieValue) {

        Puppet thisPuppet=gp.getPuppet();
        Puppet oppoPuppet=gp.getNextPuppet();

        int countUp=0;
        int countDown=0;

        int opponentIndex= oppoPuppet.getCellIndex();


        for(int i=opponentIndex+1; i<opponentIndex+maxDieValue; i++){
            Connection con=gp.getConnectionAt(GamePane.cellToLocation(i));
            if(con!=null){
                int start=con.cellStart;
                int end=con.cellEnd;
                if(start<end){
                    countUp++;
                }
                if(start>end) {
                    countDown++;
                }
            }
        }
        if(countUp>=countDown){
            gp.reverseAllConnection();
            System.out.println("SimpleStrategy applied");
        }

    }
}
