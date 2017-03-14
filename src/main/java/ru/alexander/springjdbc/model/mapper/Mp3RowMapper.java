package ru.alexander.springjdbc.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ru.alexander.springjdbc.model.Author;
import ru.alexander.springjdbc.model.Mp3;

public class Mp3RowMapper implements RowMapper<Mp3>{

    @Override
    public Mp3 mapRow(ResultSet rs, int i) throws SQLException {
        Mp3 mp3 = new Mp3();
        Author author = new Author();
        mp3.setId(rs.getInt("mp3_id"));
        mp3.setName(rs.getString("mp3_name"));
        author.setId(rs.getInt("author_id"));
        author.setName(rs.getString("author_name"));
        mp3.setAuthor(author);
        return mp3;
    }
    
}
