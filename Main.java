import javax.swing.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/eventos_db";
        final String USER = "root"; final String PASSWORD = "root";
        final String CONSULTA = "select * from evento";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER,PASSWORD);
            System.out.println("Conectado!");
            Statement st = con.createStatement();
            System.out.println("Statement criado!");
            String query = "insert into evento (nome, local, data, capacidade) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);

            String nome = JOptionPane.showInputDialog("Digite o nome do evento:");
            String local = JOptionPane.showInputDialog("Insira o local do envento:");
            String data = JOptionPane.showInputDialog("Insira a data do evento:");
            String capacidade = JOptionPane.showInputDialog("Informe a capacidade do envento:");

            stmt.setString(1, nome);
            stmt.setString(2, local);
            stmt.setString(3, data);
            stmt.setString(4, capacidade);

            int linhasAfetadas = stmt.executeUpdate(); System.out.println("Dados inseridos!");
            ResultSet resultSet = stmt.executeQuery(CONSULTA);

            while(resultSet.next()){
                System.out.println(resultSet.getString("nome"));
                System.out.println(resultSet.getString("local"));
                System.out.println(resultSet.getString("data"));
                System.out.println(resultSet.getString("capacidade"));
            }
        }
        catch (Exception e){ System.out.println(e);
            e.printStackTrace();
        }
    }
}