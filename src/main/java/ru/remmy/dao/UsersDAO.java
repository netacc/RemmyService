package ru.remmy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.remmy.entities.User;
import ru.remmy.entities.UserList;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsersDAO implements IUsersDAO {

    Map<String, User> profsMap = new HashMap<String, User>();

    DataSource dataSource;

    private SimpleJdbcInsert insertUser;
    private JdbcTemplate templateUser;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.templateUser = new JdbcTemplate(this.dataSource);
        this.insertUser = new SimpleJdbcInsert(this.dataSource).withTableName("users");
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUser(String id) {
        try{
            User user = (User)templateUser.queryForObject(
                    "SELECT * FROM users WHERE id = '" + id + "'",
                    new RowMapper<User>() {
                        public User mapRow(ResultSet rs, int rowNum)
                                throws SQLException {
                                User User = new User();
                                User.setName(rs.getString("name"));
                                User.setId(rs.getInt("id"));
                                return User;
                        }
                    });

            return user;
        } catch (EmptyResultDataAccessException e) {
        return null;
    }
}

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean removeUser(String id) {
        return false;
    }

//    @Override
//    public ArrayList<User> getUserList() {
//        ArrayList<User> userList = (ArrayList<User>) templateUser.query(
//                "SELECT * FROM users;", new RowMapper<User>() {
//                    public User mapRow(ResultSet rs, int rowNum)
//                            throws SQLException {
//                        User user = new User();
//                        user.setId(rs.getInt("id"));
//                        user.setName(rs.getString("name"));
//                        return user;
//                    }
//                });
//
//        return userList;
//    }

    @Override
    public UserList getUserList(){
        ArrayList<User> _userList = (ArrayList<User>) templateUser.query(
                "SELECT * FROM users;", new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        return user;
                    }
                });
        UserList userList = new UserList();
        userList.addAll(_userList);
        return userList;
    }
}
