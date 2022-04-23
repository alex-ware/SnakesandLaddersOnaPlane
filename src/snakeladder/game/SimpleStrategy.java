package snakeladder.game;

import javax.xml.stream.Location;

public class SimpleStrategy implements Strategy{

    GamePane gp;
    public SimpleStrategy(GamePane gp){
        this.gp=gp;
    }

    @Override
    public void doStrategy(int maxDieValue) {


        Puppet oppoPuppet=gp.getPuppet();
        if(oppoPuppet!=null){
            int countUp=0;
            int countDown=0;

            int opponentIndex= oppoPuppet.getCellIndex();
            System.out.println("Oppo Cell index: "+opponentIndex);
            System.out.println("Oppo" + oppoPuppet.getPuppetName());

            for(int i=opponentIndex+1; i<=opponentIndex+maxDieValue; i++){
                //break loop when exceed cell index
                if(i==gp.NUMBER_HORIZONTAL_CELLS* gp.NUMBER_VERTICAL_CELLS)
                    break;
                Connection con=gp.getConnectionAt(GamePane.cellToLocation(i));
                if(con!=null){
                    System.out.println("Find Connection");
                    int start=con.getCellStart();
                    int end=con.getCellEnd();
                    System.out.println("start: "+start);
                    System.out.println("end: "+end);
                    if(start<end){
                        countUp++;
                    }
                    if(start>end) {
                        countDown++;
                    }
                }

            }
            System.out.println("countUP:"+ countUp);
            System.out.println("countDOWN:"+ countDown);
            if(countUp>=countDown){
                gp.reverseAllConnection();
                System.out.println("SimpleStrategy applied");
            }
        }


    }
}
