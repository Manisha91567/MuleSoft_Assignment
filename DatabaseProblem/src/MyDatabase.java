import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class MyDatabase {
	
	static Scanner scan= new Scanner(System.in);
	public static void main(String[] args) {
		int choice;
		MyDatabase var =new MyDatabase();
		String movieName,actorName,directorName,releaseDate;
		do
		{
			choice= var.Dialog();
			if(choice==1)
			{
				scan.nextLine();
				System.out.println("Movie Name :");
				movieName= scan.nextLine();
				
				System.out.println("Lead Actor/Actress Name :");
				actorName= scan.nextLine();
				
				System.out.println("Director Name :");
				directorName= scan.nextLine();
				
				System.out.println("Release Date :");
				releaseDate= scan.nextLine();
				var.Insert(movieName, actorName, directorName, releaseDate);
			}else if(choice ==2)
			{
				var.Retrive();
			}
		
			
		}while(choice!=3);
	}
	
	int Dialog()
	{
		int ch;
		System.out.println("Sql Curd Operations (Enter a number to proceed).");
		System.out.println("1. Insert Movie Detail");
		System.out.println("2. Retrive Movies");
		System.out.println("3. Exit");
		ch=scan.nextInt();
		return ch;
	}
	
	
	void Retrive() {
		   try {
			   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Manisha", "root", "root");//Establishing connection
			   System.out.println("Connected With the database successfully");
			   //Using SQL SELECT Query
			           PreparedStatement preparedStatement=connection.prepareStatement("select * from Movies");
			           //Creating Java ResultSet object
			           ResultSet resultSet=preparedStatement.executeQuery();
			           if(resultSet.next()== false)
			           {
			        	   System.out.println("No Record Found");
			           }else {
			        	   do{
				                String movieName=resultSet.getString("MOVIENAME");
				                String actorName=resultSet.getString("ACTORNAME");
				                String directorName=resultSet.getString("DIRECTORNAME");
				                String releasedate=resultSet.getString("RELEASEDATE");
				                //Printing Results
				                
				                System.out.println(movieName+" "+actorName+" "+directorName+" "+releasedate+"\n");
				           }while(resultSet.next());
			           }
			           
			           
			           
			   } catch (SQLException e) {
			   System.out.println("Error while connecting to the database");
			   }
	}
	
	void Insert(String movieName,String actorName,String directorName,String releaseDate)
	{
		  try {
			  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Manisha", "root", "root");//Establishing connection
			  System.out.println("Connected With the database successfully");
			  //Crating PreparedStatement object
			  PreparedStatement preparedStatement=connection.prepareStatement("insert into Movies values(?,?,?,?)");
			  //Setting values for Each Parameter
			  preparedStatement.setString(1,movieName);
			           preparedStatement.setString(2,actorName);
			           preparedStatement.setNString(3, directorName);
			           preparedStatement.setNString(4, releaseDate);
			          
			           //Executing Query
			           preparedStatement.executeUpdate();
			           System.out.println("data inserted successfully");
			  } catch (SQLException e) {
			  System.out.println("Error while connecting to the database");
			  }
	}
}
