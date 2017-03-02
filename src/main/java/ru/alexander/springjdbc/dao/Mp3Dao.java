package ru.alexander.springjdbc.dao;

import java.util.List;
import ru.alexander.springjdbc.model.Mp3;

public interface Mp3Dao {

    void insert(Mp3 mp3);

    void delete(int id);

    Mp3 getById(int id);

    List<Mp3> getListByName(String name);

    List<Mp3> getListByAuthor(String name);

}
