import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactClient {
    public static void main(String args[]) throws SQLException {
        System.out.println("个人通讯录");

        while (true) {
            System.out.println("Enter command (ADD, UPDATE, DELETE, VIEW, EXIT): ");
            Scanner input = new Scanner(System.in);
            String op = input.next();
            ContactServer jdbc = new ContactServer();

            switch (op) {
                case "VIEW": {
                    System.out.println("Enter name:");
                    ResultSet rs = jdbc.select_name(input.next());

                    System.out.println("id     name     location     phone");
                    if (rs != null) {
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "     "
                                    + rs.getString(2) + "     "
                                    + rs.getString(3) + "     "
                                    + rs.getString(4));
                        }
                    }
                    break;
                }
                case "ADD": {
                    String name, location, phone;
                    System.out.println("Enter name:");
                    name = input.next();
                    System.out.println("Enter location:");
                    location = input.next();
                    System.out.println("Enter phone:");
                    phone = input.next();

                    if (jdbc.insert(name, location, phone)) {
                        System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAIL");
                    }
                    break;
                }
                case "UPDATE": {
                    int id;
                    System.out.println("Enter ID:");
                    id = input.nextInt();
                    String name, location, phone;
                    System.out.println("Enter name:");
                    name = input.next();
                    System.out.println("Enter location:");
                    location = input.next();
                    System.out.println("Enter phone:");
                    phone = input.next();

                    jdbc.update_name(id, name, location, phone);
                    break;
                }
                case "DELETE": {
                    System.out.println("Enter ID:");
                    int id = input.nextInt();

                    if (jdbc.delete(id)) {
                        System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAIL");
                    }
                    break;
                }
            }
        }
    }
}
