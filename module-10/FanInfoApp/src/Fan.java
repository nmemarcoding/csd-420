
// This class represents a Fan with properties
// such as ID, first name, last name, and favorite team.
// It includes getter and setter methods for each property.
// It is used to encapsulate the data related to a fan.


public class Fan {

  private int id;
  private String firstName;
  private String lastName;
  private String favoriteTeam;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = new String(firstName);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = new String(lastName);
  }

  public String getFavoriteTeam() {
    return favoriteTeam;
  }

  public void setFavoriteTeam(String favoriteTeam) {
    this.favoriteTeam = new String(favoriteTeam);
  }
}
