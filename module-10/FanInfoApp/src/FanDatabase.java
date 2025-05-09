
// This class is responsible for managing the database
// connection and performing CRUD operations on the Fan table.
// It includes methods to get a fan by ID and update a fan's information.

import java.sql.*;

public class FanDatabase {

  private Connection con;

  public FanDatabase() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/databasedb?";
      con = DriverManager.getConnection(url + "user=student1&password=pass");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Fan getFanById(int id) {
    Fan fan = null;

    try {
      String sql = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setInt(1, id);
      ResultSet rs = pst.executeQuery();

      if (rs.next()) {
        fan = new Fan();
        fan.setId(id);
        fan.setFirstName(rs.getString("firstname"));
        fan.setLastName(rs.getString("lastname"));
        fan.setFavoriteTeam(rs.getString("favoriteteam"));
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return fan;
  }

  public boolean updateFan(Fan fan) {
    try {
      String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setString(1, fan.getFirstName());
      pst.setString(2, fan.getLastName());
      pst.setString(3, fan.getFavoriteTeam());
      pst.setInt(4, fan.getId());

      int rows = pst.executeUpdate();
      return rows > 0;

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
