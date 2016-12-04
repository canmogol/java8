package com.fererlab.java8.enterprise;

public interface UserService {

    void setDao(UserDAO dao);

    String findUsername(Integer id);

}
