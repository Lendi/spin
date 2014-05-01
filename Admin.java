import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver"); //JDBC Driver for mySQL
      connect = DriverManager
          .getConnection("mysql -u root -p"
              + "user=sqluser&password=sqluserpw"); // connecting to the database

      statement = connect.createStatement(); // Allows to connect sql query to database

      resultSet = statement
          .executeQuery("select * from student_details"); // Result of the sql query
      writeResultSet(resultSet);

      
      preparedStatement = connect
          .prepareStatement("insert into student_details values (?, ?, ?, ? , ?)"); 
      preparedStatement.setString(1, "rollno");
      preparedStatement.setString(2, "name");
      preparedStatement.setString(3, "branch");
      preparedStatement.setInt(4, "phno");
      preparedStatement.setInt(5, "parent_phno");
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT rollno, name,branch from student_details");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

     
      preparedStatement = connect
      .prepareStatement("delete from student_details where myuser= ? ; ");
      preparedStatement.setString(1, "rollno");
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from student_details");
      writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    // now get some metadata from the database
    System.out.println("The columns in the table are: ");
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // resultSet is initialised before the first data set
    while (resultSet.next()) {
      // it is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
     
      String roll = resultSet.getString("rollno");
      String name = resultSet.getString("name");
      String branch = resultSet.getString("branch");
      String phn = resultSet.getInt("phno");
      System.out.println("rollno: " + roll);
      System.out.println("name: " + name);
      System.out.println("branch: " + branch);
      System.out.println("phone num: " +phno);
      
    }
  }

 
  private void close() {
    close(resultSet);
    close(statement);
    close(connect);
  }
  private void close(Closeable c) {
    try {
      if (c != null) {
        c.close();
      }
    } catch (Exception e) {
   
    }
  }
} 


public class Admin {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    dao.readDataBase();
  }

} 