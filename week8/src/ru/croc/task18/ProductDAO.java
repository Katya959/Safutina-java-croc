package ru.croc.task18;

import java.sql.*;

public class ProductDAO {
    //Product в предыдущем задании я записывала как Item.
    static final String DB_Driver = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:/Users/ekaterinasafutina/Desktop/week8";

    static final String USER = "Kate";
    static final String PASS = "Bonya2020";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    String title;
    Integer price;
    String article;

    ProductDAO(String article1, String title1, Integer price1) {
        this.article = article1;
        this.title = title1;
        this.price = price1;
    }

    //Поиск в базе данных товара с указанным артикулом.
    // Если соответствующего товара в базе данных нет, метод возвращает null.
    public static ProductDAO findProduct(String productCode) throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        ProductDAO result = null;

        String sql = "SELECT * FROM ARTICLE_ITEMS WHERE articleOrder = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String article = resultSet.getString("articleOrder");
                    String title = resultSet.getString("title");
                    Integer price = resultSet.getInt("price");
                    result = new ProductDAO(article, title, price);
                }
            }
        }

        connection.close();
        return result;
    }

    //Создание нового товара. Если в базе данных
    // существует товар с переданным артикулом, метод выбрасывает исключение.
    public static ProductDAO createProduct(ProductDAO product) throws Exception {
        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();

        ProductDAO product1;
        String title = product.title;   //название
        Integer price = product.price;   //цена
        String article = product.article;   //артикул
        product1 = findProduct(article);
            if (product1 == null) {
                //Т. е. такого продукта еще нет в таблице.
                String addProduct = "INSERT INTO ARTICLE_ITEMS (title, price, articleOrder) \n" +
                        " VALUES ('" + title + "', " + price + ", " + "'" + article + "')";
                statement.executeUpdate(addProduct);
            }
            //else {
                //Не смогла выбросить исключение, чтобы программа дальше так же без ошибок работала.
                //System.out.println("Продукт с таким артикулом уже существует в базе данных!");
            //}
        return new ProductDAO(article, title, price);
    }

    //Изменение информации о товаре. Название и цена товара в базе данных
    // заменяется на значения, указанные в полях параметра product. Артикул
    // товара, данные которого должны быть изменены, также задается полем объекта product.
    public static ProductDAO updateProduct(ProductDAO product) throws SQLException, ClassNotFoundException {
        ProductDAO result;
        String title = product.title;   //название
        Integer price = product.price;   //цена
        String article = product.article;   //артикул

        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);


        String sql = "UPDATE ARTICLE_ITEMS SET title = ?, price = ? WHERE articleOrder = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setInt(2, price);
            statement.setString(3, article);
            statement.executeUpdate();
        }
        result = new ProductDAO(article, title, price);
        return result;
    }


    //Удаление товара и всех упоминаний о нем в заказах.
    // Вас смущает необходимость изменения уже выданных заказов, но заказчик настаивает.
    public static String deleteProduct(String productCode) throws ClassNotFoundException, SQLException {
        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql1 = "DELETE FROM ORDERS_OF_BUYERS WHERE article = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setString(1, productCode);
            statement.executeUpdate();
            connection.close();
        }

        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        String sql = "DELETE FROM ARTICLE_ITEMS WHERE articleOrder = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productCode);
            statement.executeUpdate();
            connection.close();
        }
        return "Продукт удален из обеих таблиц!";
    }

    @Override
    public String toString() {
        return "ProductDAO{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", article='" + article + '\'' +
                '}';
    }
}
