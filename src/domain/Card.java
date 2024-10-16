package domain;
public class Card {
  private String value;
  private String type;
  int ACE_VALUE = 11;
  int NON_NUMBER_VALUE = 10;

  public Card(String value, String type) {
      this.value = value;
      this.type = type;
  }

  public int getValue() {
      if (value.equals("A")) {
          return ACE_VALUE;
      } else if ("JQK".contains(value)) {
          return NON_NUMBER_VALUE;
      } else {
          return Integer.parseInt(value);
      }
  }

  public boolean isAce() {
      return value.equals("A");
  }

  public String getImagePath() {
      return "../assets/cards/" + value + "-" + type + ".png";
  }

  @Override
  public String toString() {
      return value + "-" + type;
  }
}
