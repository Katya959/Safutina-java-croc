package ru.croc.task17;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_Driver = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:/Users/ekaterinasafutina/Desktop/week8";

    static final String USER = "Kate";
    static final String PASS = "Bonya2020";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(DB_Driver);     //Проверяем наличие JDBC драйвера для работы с БД
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(DB_URL, USER, PASS);     //соединение с БД
            // getting Statement object to execute query
            statement = connection.createStatement();

            //Создала запрос
            String INFORMATION_OF_ITEMS = "CREATE TABLE ARTICLE_ITEMS" +
                    "(title VARCHAR(255) NOT NULL, " +
                    " price INTEGER NOT NULL, " +
                    "articleOrder VARCHAR(255) PRIMARY KEY)";
            statement.executeUpdate(INFORMATION_OF_ITEMS);   //выполнен

            String CREATE_ORDERS_OF_BUYERS = "CREATE TABLE ORDERS_OF_BUYERS " +
                    "(idOrder INTEGER NOT NULL, " +
                    "login VARCHAR(255) NOT NULL, " +
                    "article VARCHAR(255), " +
                    "foreign key (article) references ARTICLE_ITEMS(articleOrder))";
            statement.executeUpdate(CREATE_ORDERS_OF_BUYERS);  //выполнен

            String file = args[0];
            Integer k = 0;
            if (new File(file).exists()) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File(file));
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        String[] array = line.split(",");
                        int idOrder = Integer.parseInt(array[0]);
                        String login = array[1];
                        String article = array[2];
                        String title = array[3];
                        int price = Integer.parseInt(array[4]);

                        //В данном случае, если будет похожая строчка (с одним и тем же articleOrder),
                        //то вызывается исключение, а оно получается автоматически, не допускает записи такой
                        //строчки в бд, потому что запись с таким ключом в бд уже есть!
                        try {
                            String addItem = "INSERT INTO ARTICLE_ITEMS (title, price, articleOrder) \n" +
                                    " VALUES ('" + title + "', " + price + ", " + "'" + article + "')";
                            statement.executeUpdate(addItem);

                            String addOrder1 = "INSERT INTO ORDERS_OF_BUYERS (idOrder, login, article) \n" +
                                    " VALUES (" + idOrder + ", '" + login + "', " + "'" + article + "')";
                            statement.executeUpdate(addOrder1);
                        }
                        catch (SQLException e) {}
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    scanner.close();
                }

                //resultSet.close();   //для того, чтобы проверить при выводе, какие данные выводятся из баз данных и
                //правильно ли они были записаны; в нашей программе, при записи данных в БД не нужно!
            }
            //Все, что открыли, закрываем!
            connection.close();
            statement.close();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Что-то пошло не так.");
        }
    }
}

