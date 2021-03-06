package ru.alexander.springjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alexander.springjdbc.dao.Mp3Dao;
import ru.alexander.springjdbc.model.Author;
import ru.alexander.springjdbc.model.Mp3;

public class Start {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Mp3Dao mp3Dao = (Mp3Dao) context.getBean("sqliteDao");

        Author author = new Author();
        author.setName("authorName");
        
        Mp3 mp3 = new Mp3();
        mp3.setName("song name");
        mp3.setAuthor(author);

        System.out.println(mp3Dao.insert(mp3));
//        System.out.println(mp3Dao.delete(10));
//        System.out.println(mp3Dao.getListByName("song name"));
//        System.out.println(mp3Dao.getCount());
//        System.out.println(mp3Dao.getStat());
//        mp3Dao.getErrorExample();
//       System.out.println(mp3Dao.getById(2));

    }
}
