package com.fererlab.java8.enterprise;

import java.util.HashMap;
import java.util.Map;

public class UserDAOStaticImpl implements UserDAO {

    private static Map<Integer, UserEntity> userEntityMap = new HashMap<Integer, UserEntity>() {{
        put(1, new UserEntity(1, "john"));
        put(2, new UserEntity(2, "wick"));
    }};

    @Override
    public UserEntity findById(Integer id) {
        return userEntityMap.get(id);
    }
}
