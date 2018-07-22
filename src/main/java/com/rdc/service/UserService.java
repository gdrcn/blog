package com.rdc.service;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Album;
import com.rdc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private CommentDao commentDao;

    public User getUserInfo(int id) {
        User user = userDao.getUserInfo(id);
        user.setAlbumList(albumDao.getUserAlbum(id));
        user.setFans((userDao.getFansNum(id)).length);
        user.setIdols((userDao.getIdolsNum(id)).length);
        user.setNotReadComment((commentDao.getAlbumComment(id).size() + (commentDao.getblogFirstComment(id).size())) + (commentDao.getBlogSecondComment(id)).size());
        return user;
    }

    public String updateUserInfo(User user){
        System.out.println("??????????");
        String emailRegularExpression = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String addressRegularExpression = "^[a-zA-Z0-9\u4E00-\u9FA5]{0,20}$";
        String phoneRegularExpression = "^[0-9]{0,12}$";
        if((user.getEmail() != null)){
           if(!(user.getEmail().matches(emailRegularExpression))) {
               return "emailError";
           }
        }else if((user.getAge() != null)){
            if((user.getAge()> 150 || user.getAge() < 0)){
                return "ageError";
            }
        }else if(user.getAddress() != null){
            if(!(user.getAddress()).matches(addressRegularExpression)){
                return "addressError";
            }
        }else if(user.getPhone() != null){
            if(user.getPhone().matches(phoneRegularExpression)){
                return "phoneError";
            }
        }
        System.out.println("3333333333");
//        userDao.updateUserInfo(user);
        return null;
    }
}
