package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserService {

   private final UserDao userDao;

   @Autowired
   public UserService(UserDao userDao) {
      this.userDao = userDao;
   }

   public List<User> getAllUsers() {
      return userDao.findAll();
   }

   @Transactional
   public void saveUser(User user) {
      userDao.save(user);
   }

   @Transactional
   public void removeUser(long id) {
      userDao.deleteById(id);
   }

   @Transactional
   public void updateUser(User user) {
      userDao.save(user);
   }

   @Transactional
   public User getUser(long id) {
      return userDao.getReferenceById(id);
   }
}