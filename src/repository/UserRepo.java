package repository;

import data.Article;
import data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserRepo {
    public void creatArticleTable() throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("create table articletbl" +
                " ( id int primary key not null," +
                " username varchar(50) not null," + " nationalCod varchar(50) not null," +
                " password varchar (50) not null," + " creatDate Date not null," + " iPublished boolean ," + " userId int not null)");
        preparedStatement.executeUpdate();
        connection.close();
    }

    public void creatUserTable() throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("create table usertable " +
                " ( id int primary key not null," +
                " title varchar(50) not null," + " brief varchar(50) not null," +
                " content varchar (50) not null," + " birthday Date not null)");
        preparedStatement.executeUpdate();
        connection.close();
    }

    public boolean logIn(String username, String password) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from usertable where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null)
            return true;
        return false;
    }

    public void editMyArticle(int id, String title, String brief, String content, Date createDate, boolean ispublished, int userId) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE articletbl" +
                " SET title=?,brief=?, content=?,creatDate=?,isPublished=? " +
                "WHERE id=?,userId=?;");


        preparedStatement.setString(1, title);
        preparedStatement.setString(2, brief);
        preparedStatement.setString(3, content);
        preparedStatement.setDate(4, (java.sql.Date) createDate);
        preparedStatement.setBoolean(5, ispublished);
        preparedStatement.setInt(6, id);
        preparedStatement.setInt(7, userId);

        preparedStatement.executeUpdate();
        connection.close();

    }

    public List<Article> viewMyArticle(int id) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select from articletbl " +
                "where userId=? )");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Article> list = new ArrayList<>();
        while (resultSet.next()) {
            Article article = null;
            article = new Article(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5),
                    resultSet.getBoolean(6),
                    resultSet.getInt(7)
            );
            list.add(article);
        }
        connection.close();
        return list;
    }

    public void insertArticle(Article article) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into articletbl values (?,?,?,?,?,?,?)");
        {
            preparedStatement.setInt(1, article.getId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getBrief());
            preparedStatement.setString(4, article.getContent());
            preparedStatement.setDate(5, (java.sql.Date) article.getCreatDate());
            preparedStatement.setBoolean(6, article.isPublished());
            preparedStatement.setInt(7, article.getUserId());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
    public void changePassword(int id, String password) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usertable" +
                "set password=? where id=?");
        preparedStatement.setString(1,password );
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        connection.close();
    }
    public void register(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into usertable values (?,?,?,?,?)");
        preparedStatement.setInt(1, user.getId() );
        preparedStatement.setString(2, user.getUsername() );
        preparedStatement.setString(3, user.getNationalCode() );
        preparedStatement.setString(4, user.getPassword() );
        preparedStatement.setDate(5, (java.sql.Date) user.getBirthday());
        preparedStatement.executeUpdate();
        connection.close();
    }
    public List<Article> viewArticle( ) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select title ,brief from articletbl"+
                " where isPublished=? ");
        preparedStatement.setBoolean(1, true );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Article> list = new ArrayList<>();
        while (resultSet.next()) {
            Article article = null;
            article = new Article(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            list.add(article);
        }
        connection.close();
        return list;
    }

    public Article viewCompleteArticle(int id) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select title ,brief from articletbl" +
                " where id? ");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Article article = null;
        while (resultSet.next()) {

            article = new Article(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5),
                    resultSet.getBoolean(6),
                    resultSet.getInt(7)
                    );
        }
        connection.close();
        return article;
    }
}

