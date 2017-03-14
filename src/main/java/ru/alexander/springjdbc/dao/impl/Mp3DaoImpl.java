package ru.alexander.springjdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.alexander.springjdbc.dao.Mp3Dao;
import ru.alexander.springjdbc.model.Author;
import ru.alexander.springjdbc.model.Mp3;
import ru.alexander.springjdbc.model.mapper.Mp3RowMapper;

@Component("sqliteDao")
public class Mp3DaoImpl implements Mp3Dao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    //Внедряется из контекста (xml-файла)
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public int insert(Mp3 mp3) {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        int author_id = insertAuthor(mp3.getAuthor());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO mp3 (name111, author_id) VALUES (:name, :author_id)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author_id", author_id);
        this.jdbcTemplate.update(sql, params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM mp3 WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return this.jdbcTemplate.update(sql, params);
    }

    @Override
    public Mp3 getById(int id) {
        String sql = "SELECT * FROM mp3_view WHERE mp3_id=:mp3_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mp3_id", id);
        return this.jdbcTemplate.queryForObject(sql, params, new Mp3RowMapper());
    }

    @Override
    public List<Mp3> getListByName(String name) {
        String sql = "SELECT * FROM mp3 WHERE upper(name) LIKE :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", '%' + name.toUpperCase() + '%');
        return this.jdbcTemplate.query(sql, params, new Mp3RowMapper());
    }

    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM mp3";
        return this.jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    @Override
    public Map<String, Integer> getStat() {
        String sql = "SELECT author_id, count(*) AS count FROM mp3 GROUP BY author_id";
        return this.jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<>();
                while (rs.next()) {
                    map.put(rs.getString("author_id"), rs.getInt("count"));
                }
                return map;
            }
        });
    }

    @Override
    public void getErrorExample() {
        String sql = "INSERT INTO mp3 (name111, author_id) VALUES ('1', '1')";
        this.jdbcTemplate.update(sql, new MapSqlParameterSource());
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    private int insertAuthor(Author author){
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        KeyHolder keyHolder = new GeneratedKeyHolder();            
        String sql = "INSERT INTO author (name) VALUES (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());
        this.jdbcTemplate.update(sql, params, keyHolder);
        return keyHolder.getKey().intValue();
    }

}
