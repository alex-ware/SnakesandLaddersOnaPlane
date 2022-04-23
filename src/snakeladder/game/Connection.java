package snakeladder.game;

import ch.aplu.jgamegrid.Location;

public abstract class Connection
{
  Location locStart;
  Location locEnd;
  int cellStart;
  int cellEnd;
  // for task 4.a
  boolean isReversed=false;
  public int getCellStart(){return cellStart;}
  public int getCellEnd(){return cellEnd;}

  //for task 4.a
  // if a player reverse a connection, set the connection to reversed,
  // switch back when a player's turn is over
  public void reverse() {

    isReversed = !isReversed;
    int temp=cellEnd;
    cellEnd=cellStart;
    cellStart=temp;
    //set new loc
    locStart = GamePane.cellToLocation(cellStart);
    locEnd = GamePane.cellToLocation(cellEnd);
  }
  //for task 4.a
  public void setReversed(boolean isReversed){
    if(this.isReversed==isReversed) return;
    else{
      reverse();
    }
  }
  //for task 4.a
  public boolean isReversed() {
    return isReversed;
  }

  Connection(int cellStart, int cellEnd)
  {
    this.cellStart = cellStart;
    this.cellEnd = cellEnd;
    locStart = GamePane.cellToLocation(cellStart);
    locEnd = GamePane.cellToLocation(cellEnd);
  }

  String imagePath;

  public Location getLocStart() {
    return locStart;
  }

  public Location getLocEnd() {
    return locEnd;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public double xLocationPercent(int locationCell) {
    return (double) locationCell / GamePane.NUMBER_HORIZONTAL_CELLS;
  }
  public double yLocationPercent(int locationCell) {
    return (double) locationCell / GamePane.NUMBER_VERTICAL_CELLS;
  }
}
