package com.hms.user.registry.DAO;

import com.hms.user.registry.model.User;
import com.hms.user.registry.model.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String ADD_USER = "insert into user(id,name,email) values (?,?,?)";
    private final String FIND_ALL_USER = "select * from user";
    private final String FIND_USER = "select * from user where id = ?";
    private final String UPDATE_USER = "update user set name = ? , email = ? where id = ?";
    private final String DELETE_USER = "delete from user where id = ?";


    @Override
    public User getUserById(Long id) {
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(FIND_USER,new UserMap(),new Object[] {id});
        }catch (Exception e){
            System.out.println("Exception :" +e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(FIND_ALL_USER,new UserMap());
    }

    @Override
    public boolean deleteUser(Long id) {
        return jdbcTemplate.update(DELETE_USER,new Object[] {id}) >0;
    }

    @Override
    public User updateUser(Long id, User user) {
        jdbcTemplate.update(UPDATE_USER,new Object[]{user.getName(),user.getEmail(),id});
        return  getUserById(id);
    }

    @Override
    public User createUser(User user) {
        jdbcTemplate.update(ADD_USER,new Object[]{user.getId(),user.getName(),user.getEmail()});
        return getUserById(user.getId());
    }
}
