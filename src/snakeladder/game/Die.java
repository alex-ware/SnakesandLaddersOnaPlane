package snakeladder.game;

import ch.aplu.jgamegrid.Actor;

public class Die extends Actor
{
  private NavigationPane np;
  private int nb;
  //
  private Cup cup;
  private int index;

  Die(int nb, Cup cup, int index)
  {
    super("sprites/pips" + nb + ".gif", 7);
    this.nb = nb;
    this.cup = cup;
    this.index = index;
  }

  public void act()
  {
    showNextSprite();
    if (getIdVisible() == 6)
    {
      setActEnabled(false);

      // tell Cup this die is finished
      cup.finishedRolling(index);

    }
  }

}
