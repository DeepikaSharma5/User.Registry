package com.hms.user.registry.DAO;

import com.hms.user.registry.model.User;
import com.hms.user.registry.model.UserRowMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String ADD_USER_QUERY = "insert into user(id,name,email) values (?,?,?)";
    private final String FIND_ALL_USER_QUERY = "select * from user";
    private final String FIND_USER_QUERY = "select * from user where id = ?";
    private final String UPDATE_USER_QUERY = "update user set name = ? , email = ? where id = ?";
    private final String DELETE_USER_QUERY = "delete from user where id = ?";


    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(FIND_USER_QUERY, new UserRowMap(), id);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(FIND_ALL_USER_QUERY, new UserRowMap());
    }

    @Override
    public boolean deleteUser(Long id) {
        return jdbcTemplate.update(DELETE_USER_QUERY, id) > 0;
    }

    @Override
    public User updateUser(Long id, User user) {
        jdbcTemplate.update(UPDATE_USER_QUERY, user.getName(), user.getEmail(), id);
        return getUserById(id);
    }

    @Override
    public User createUser(User user) {
        jdbcTemplate.update(ADD_USER_QUERY, user.getId(), user.getName(), user.getEmail());
        return getUserById(user.getId());
    }
}
