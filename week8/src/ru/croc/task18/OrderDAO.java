package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    static final String DB_Driver = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:/Users/ekaterinasafutina/Desktop/week8";

    static final String USER = "Kate";
    static final String PASS = "Bonya2020";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    Integer order;
    String login;
    String article;

    public OrderDAO(Integer order1, String login1, String article1) {
        this.order = order1;
        this.login = login1;
        this.article = article1;
    }

    //Создание заказа. Для указанного пользователя в базе данных создается новый заказ с заданным списком товаров.
    public static void createOrder(String userLogin, List<ProductDAO> products) throws Exception {
        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();

        Integer numberOfOrderForUserLogin = 1;

        String selectOrders = "SELECT * FROM ORDERS_OF_BUYERS";
        resultSet = statement.executeQuery(selectOrders);
        while(resultSet.next()) {
            Integer idOrder = resultSet.getInt(1);
            if ((idOrder) >= numberOfOrderForUserLogin)
                numberOfOrderForUserLogin = idOrder;
        }
        resultSet.close();
        statement.close();
        connection.close();
        //Номер заказа нашего пользователя: номер последнего (самого большого по номеру) заказа в таблице + 1;
        numberOfOrderForUserLogin = numberOfOrderForUserLogin + 1;


        //Устанавливаем соединение для записи в таблицу заказов новых данных.
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
        for (ProductDAO product: products) {
            String article = product.article;

            ProductDAO.createProduct(new ProductDAO(product.article, product.title, product.price));
            String addOrder = "INSERT INTO ORDERS_OF_BUYERS (idOrder, login, article) \n" +
                    " VALUES (" + numberOfOrderForUserLogin + ", '" + userLogin + "', " + "'" + article + "')";
            statement.executeUpdate(addOrder);

        }
        connection.close();
        statement.close();
    }
}
