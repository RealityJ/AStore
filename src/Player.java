
public class Player {
  private String name;
  private int score;

  public Player(String name, int score) {
    this.name = name;
    this.score = score;
  }
  
  public int getScore() {
    return score;
  }
  
  public String getName() {
    return name;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public void setName(String name) {
    this.name = name;
  }

}
