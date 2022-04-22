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
  Connection(int cellStart, int cellEnd)
  {
    this.cellStart = cellStart;
    this.cellEnd = cellEnd;
    locStart = GamePane.cellToLocation(cellStart);
    locEnd = GamePane.cellToLocation(cellEnd);
  }

  String imagePath;

  //for task 4.a
  // if a player reverse a connection, set the connection to reversed,
  // switch back when a player's turn is over
  public void reverse() {
    isReversed = !isReversed;
    int temp=cellEnd;
    cellEnd=cellStart;
    cellStart=cellEnd;
  }
  //for task 4.a
  public boolean isReversed() {
    return isReversed;
  }

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
