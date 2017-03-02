package ru.alexander.springjdbc.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alexander.springjdbc.dao.Mp3Dao;
import ru.alexander.springjdbc.model.Mp3;

@Component("sqliteDao")
public class Mp3DaoImpl implements Mp3Dao{    
     
    private JdbcTemplate jdbcTemplate;
    
    //Внедряется из контекста (xml-файла)
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Mp3 mp3) {
        String sql = "INSERT INTO mp3 (name, author_id) VALUES (?, ?)";
        this.jdbcTemplate.update(sql, new Object[] {mp3.getName(), mp3.getAuthor()});
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM mp3 WHERE id = ?";
        int result = this.jdbcTemplate.update(sql, id);
    }

    @Override
    public Mp3 getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mp3> getListByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mp3> getListByAuthor(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
