package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepo {
    public void creatTable() throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("creat table usertbl"+
                " (id int primary key not null," +
                "username varchar(50) not null,"+ "nationalCod varchar(50) not null,"+
                "password varchar (50) not null,"+ "birthday Date not null");
        preparedStatement.executeUpdate();
    }
}
