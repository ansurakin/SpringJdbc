package ru.alexander.springjdbc.exception;

import org.springframework.dao.DataAccessException;

public class MyEception extends DataAccessException{
    
    public MyEception(String msg) {        
        super(msg);
        System.out.println("Неизвестная ошибка при работе с БД");
    }
    
}
