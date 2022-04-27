package snakeladder.game;

import ch.aplu.jgamegrid.*;
import java.awt.Point;

public class Puppet extends Actor
{
  private GamePane gamePane;
  private NavigationPane navigationPane;
  private int cellIndex = 0;
  private int nbSteps;
  private Connection currentCon = null;
  private int y;
  private int dy;
  private boolean isAuto;
  private String puppetName;
  private Strategy strategy;

  //task 2
  private boolean isLowest = false;
  //task 3
  private boolean isBack = false;
  //task 3
  public boolean isBack(){return isBack;}
  public void setBack(boolean isBack) {
    this.isBack = isBack;
  }
  //for t4
  public Strategy getStrategy() {
    return strategy;
  }

  // task 5
  private  Statistics stats;

  public Statistics getStats() {
    return this.stats;
  }

  Puppet(GamePane gp, NavigationPane np, String puppetImage, String puppetName)
  {
    super(puppetImage);
    this.gamePane = gp;
    this.navigationPane = np;
    this.puppetName = puppetName;
    //for t4
    this.strategy=new SimpleStrategy();
    this.stats = new Statistics(puppetName);
  }

  public boolean isAuto() {
    return isAuto;
  }

  public void setAuto(boolean auto) {
    isAuto = auto;
  }

  public String getPuppetName() {
    return puppetName;
  }

  public void setPuppetName(String puppetName) {
    this.puppetName = puppetName;
  }

  void go(int nbSteps)
  {
    if (cellIndex >= 100)  // after game over
    {
      cellIndex = 0;
      setLocation(gamePane.startLocation);
    }
    this.nbSteps = nbSteps;
    // task 5
    //
    if (nbSteps > 0) {
      stats.rolled(nbSteps);
    }

    // task 2
    // check if the lowest number has been rolled
    if (nbSteps == navigationPane.getNumberOfDice()) {
      isLowest = true;
    } else {
      isLowest = false;
    }

    setActEnabled(true);
  }

  void resetToStartingPoint() {
    cellIndex = 0;
    setLocation(gamePane.startLocation);
    setActEnabled(true);
  }

  int getCellIndex() {
    return cellIndex;
  }

  // deleting moveToNextCell method and replace with moveToCell

  //task 3
  // changing cell index based on no. of steps left to act
  private void moveToCell(int nbSteps) {
    if (nbSteps > 0) {
      cellIndex++;
    } else if (nbSteps < 0) {
      cellIndex--;
    }
    setLocation(GamePane.cellToLocation(cellIndex));
  }

  public void act()
  {
    if ((cellIndex / 10) % 2 == 0)
    {
      if (isHorzMirror())
        setHorzMirror(false);
    }
    else
    {
      if (!isHorzMirror())
        setHorzMirror(true);
    }

    // Animation: Move on connection
    // adding both conditions in task 2 to satisfy new rules
    if (currentCon != null && !(isLowest && currentCon.cellEnd - currentCon.cellStart < 0))
    {
      int x = gamePane.x(y, currentCon);
      setPixelLocation(new Point(x, y));
      y += dy;

      // Check end of connection
      if ((dy > 0 && (y - gamePane.toPoint(currentCon.locEnd).y) > 0)
        || (dy < 0 && (y - gamePane.toPoint(currentCon.locEnd).y) < 0))
      {
        gamePane.setSimulationPeriod(100);
        setActEnabled(false);
        setLocation(currentCon.locEnd);
        cellIndex = currentCon.cellEnd;
        setLocationOffset(new Point(0, 0));
        currentCon = null;
        navigationPane.prepareRoll(cellIndex);
      }
      return;
    }


    // Normal movement
    if (nbSteps != 0)
    {
      moveToCell(nbSteps);

      if (cellIndex >= 100)  // Game over
      {
        setActEnabled(false);
        navigationPane.prepareRoll(cellIndex);
        return;
      }

      // task 3
      if (nbSteps > 0) nbSteps--;
      if (nbSteps < 0) nbSteps++;
      if (nbSteps == 0)
      {
        // Check if on connection start
        if ((currentCon = gamePane.getConnectionAt(getLocation())) != null && !(isLowest && currentCon.cellEnd - currentCon.cellStart < 0))
        {
          gamePane.setSimulationPeriod(50);
          y = gamePane.toPoint(currentCon.locStart).y;
          if (currentCon.locEnd.y > currentCon.locStart.y)
            dy = gamePane.animationStep;
          else
            dy = -gamePane.animationStep;
          if (currentCon instanceof Snake)
          {
            navigationPane.showStatus("Digesting...");
            navigationPane.playSound(GGSound.MMM);
          }
          else
          {
            navigationPane.showStatus("Climbing...");
            navigationPane.playSound(GGSound.BOING);
          }
          // task 5 determine which connection and record it
          if (currentCon.cellEnd - currentCon.cellStart > 0) {
            stats.up();
          } else {
            stats.down();
          }
        }
        else
        {
          setActEnabled(false);
          navigationPane.prepareRoll(cellIndex);
        }
      }
    }
    //for t4
    //apply strategy at the end
    if(this!=null)
    this.strategy.doStrategy(gamePane,navigationPane.getNumberOfDice()*6);

  }

}

