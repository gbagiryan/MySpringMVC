package am.bagiryan.myspringmvc.repository;

import am.bagiryan.myspringmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class, noRollbackFor = IOException.class, readOnly = false)
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User getByName(String username) {
        NativeQuery<User> query = sessionFactory.getCurrentSession().createNativeQuery("select * from user where username = ?", User.class);
        query.setParameter(1, username);
        return query.uniqueResult();
    }

    public User getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
