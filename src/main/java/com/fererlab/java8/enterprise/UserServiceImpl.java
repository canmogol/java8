package com.fererlab.java8.enterprise;

public class UserServiceImpl implements UserService {

    private UserDAO dao;

    @Override
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public String findUsername(Integer id) {
        UserEntity userEntity = dao.findById(id);
        return userEntity.getUsername();
    }

}
