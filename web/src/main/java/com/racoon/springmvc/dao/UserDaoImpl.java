package com.racoon.springmvc.dao;

import com.racoon.springmvc.model.User;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by icemintt on 6/9/16.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    public User login(User user){
        Criteria criteria =createEntityCriteria();
        criteria.add(Restrictions.eq("username",user.getUsername()));
        criteria.add(Restrictions.eq("password",user.getPassword()));
        return (User) criteria.uniqueResult();
    }
    public User findByName(String username){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username",username));
        return (User) criteria.uniqueResult();
    }

    public User findById(int id){
        return getByKey(id);
    }

    public void saveUser(User user){
        persist(user);
    }

    public void deleteUserByName(String username){
        Query query = getSession().createSQLQuery("delete from User where username = :username");
        query.setString("username",username);
        query.executeUpdate();
    }

    public List<User> findAllUsers(){
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    public boolean isCorrectLogin(User user){
        Criteria criteria =createEntityCriteria();
        criteria.add(Restrictions.eq("username",user.getUsername()));
        criteria.add(Restrictions.eq("password",user.getPassword()));
        return (criteria.uniqueResult())!=null;
    }
}
