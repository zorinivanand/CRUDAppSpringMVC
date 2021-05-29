package ru.zorinivanand.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zorinivanand.models.Person1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;


    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person1> index(){
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person1.class));
    }

    public Person1 show(final int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Person1.class))
                .stream().findAny().orElse(null);
    }
    public void save(Person1 person1){
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)", person1.getName(),person1.getAge(),person1.getEmail());


    }

    public void update(int id, Person1 personUpdated) {
      jdbcTemplate.update("UPDATE Person SET name =?,age = ?,email = ? WHERE id = ?",personUpdated.getName(),personUpdated.getAge(),personUpdated.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
        }
    }

