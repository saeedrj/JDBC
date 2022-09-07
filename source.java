package book.bookdto;
import java.sql.*;

public class MainClass {
        public static void main(String[] args) {
            Connection con=null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/dbtest";
                String user="root";
                String password="123456";
                con=DriverManager.getConnection(url,user,password);
                Statement statement=con.createStatement();
                String sql="Create Database dbTest3";
                int result=statement.executeUpdate(sql);


                String sql="Create Table T_User(" +
                        "id Integer not Null Primary Key," +
                        "name Varchar(50) not Null)";
                int result=statement.executeUpdate(sql);
                System.out.println(result);

                String sql="insert into " +
                        "t_person(personId, name, family, age, avg)" +
                        "Values(100,'sara','akbari',20,19.2)";
                int result=statement.executeUpdate(sql);
                System.out.println(result);


                Scanner reader=new Scanner(System.in);
                int id=reader.nextInt();
                String name=reader.next();
                String family=reader.next();
                int age=reader.nextInt();
                float avg=reader.nextFloat();
                String sql="insert into t_person(personId, name, family, age, avg)Values(?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,id);
                ps.setString(2,name);
                ps.setString(3,family);
                ps.setInt(4,age);
                ps.setFloat(5,avg);
                int result=ps.executeUpdate();
                System.out.println(result);

                String sql="Update t_person set name=?,family=? where personId=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,"Ahmad");
                ps.setString(2,"Taheri");
                ps.setInt(3,103);
                int result=ps.executeUpdate();
                System.out.println(result);

                con.setAutoCommit(false);
                String sql="DELETE from t_person where personId=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,3);
                int result=ps.executeUpdate();
                System.out.println(result);
                con.commit();

                String sql="Select * from t_person";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet result=ps.executeQuery();
                while (result.next()){
                    int personId=result.getInt("personId");
                    String name=result.getString("name");
                    String family=result.getString("family");
                    int age=result.getInt("age");
                    float avg=result.getFloat("avg");
                    System.out.println(personId+"\t"+name+"\t"+family+"\t"+age+"\t"+avg);
                }


                String sql="Select * from t_person where personId=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,1003);
                ResultSet result=ps.executeQuery();
                while (result.next()){
                    int personId=result.getInt("personId");
                    String name=result.getString("name");
                    String family=result.getString("family");
                    int age=result.getInt("age");
                    float avg=result.getFloat("avg");
                    System.out.println(personId+"\t"+name+"\t"+family+"\t"+age+"\t"+avg);
                }

                con.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
            finally {
                System.out.println("finally");
            }
        }
    }


