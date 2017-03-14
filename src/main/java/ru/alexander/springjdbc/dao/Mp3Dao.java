package ru.alexander.springjdbc.dao;

import java.util.List;
import java.util.Map;
import ru.alexander.springjdbc.model.Mp3;

public interface Mp3Dao {

    int insert(Mp3 mp3);

    int delete(int id);

    Mp3 getById(int id);

    List<Mp3> getListByName(String name);

    int getCount();

    Map<String, Integer> getStat();

    void getErrorExample();

}
