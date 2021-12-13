package ru.croc.task18;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;

//Попробовала сделать максимально, что смогла.
public class Main {
    static final String DB_Driver = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:/Users/ekaterinasafutina/Desktop/week8";

    static final String USER = "Kate";
    static final String PASS = "Bonya2020";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DB_Driver);     //Проверяем наличие JDBC драйвера для работы с БД
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(DB_URL, USER, PASS);     //соединение с БД
            // getting Statement object to execute query
            statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String operation = reader.readLine();
            String[] array = operation.split(" ");
            String array0 = array[0];
            Boolean a = true;
            if (array0.equals("КОНЕЦ"))
                a = false;
            else
                a = true;

            while (a) {
                switch (array0) {
                    case "ТОВАР":
                        String article = array[1];
                        String title = array[2];
                        Integer price = Integer.parseInt(array[3]);
                        ProductDAO.createProduct(new ProductDAO(article, title, price));
                        break;
                    case "ИЗМЕНИТЬ":
                        String article1 = array[1];
                        String title1 = array[2];
                        Integer price1 = Integer.parseInt(array[3]);
                        ProductDAO.updateProduct(new ProductDAO(article1, title1, price1));
                        break;
                    case "УДАЛИТЬ":
                        String article2 = array[1];
                        ProductDAO.deleteProduct(article2);
                        break;
                    case "ЗАКАЗ":
                        List<ProductDAO> list = new ArrayList<ProductDAO>();
                        for (int i = 2; i < array.length; i++) {
                            String code = array[i];
                            ProductDAO product = ProductDAO.findProduct(code);
                            if (product != null) {
                                list.add(product);
                            }
                        }
                        OrderDAO.createOrder(array[1], list);
                        break;
                }
                if (array0.equals("КОНЕЦ")) {
                    break;
                }
                else {
                    operation = reader.readLine();
                    array = operation.split(" ");
                    array0 = array[0];
                    a = array0.equals("КОНЕЦ");
                }
            }
            statement.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
