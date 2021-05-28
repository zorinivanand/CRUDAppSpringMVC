package ru.zorinivanand.dao;

import org.springframework.stereotype.Component;
import ru.zorinivanand.models.Person1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Person1> index(){
        List<Person1> people = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String SQL="SELECT*FROM Person";
            ResultSet resultSet =  statement.executeQuery(SQL);

            while (resultSet.next()){
                Person1 person1 = new Person1();
               person1.setId(resultSet.getInt("id"));
                person1.setName(resultSet.getString("name"));
                person1.setAge(resultSet.getInt("age"));
                person1.setEmail(resultSet.getString("email"));
people.add(person1);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return people;
    }

    public Person1 show(final int id){

        Person1 person1 = null;
        try {
            PreparedStatement  preparedStatement = connection.prepareStatement
                    ("SELECT *FROM Person WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person1 = new Person1();
            person1.setId(resultSet.getInt("id"));
            person1.setName(resultSet.getString("name"));
            person1.setAge(resultSet.getInt("age"));
            person1.setEmail(resultSet.getString("email"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return person1;
    }
    public void save(Person1 person1){


        try {
           PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
           preparedStatement.setString(1,person1.getName());
           preparedStatement.setInt(2,person1.getAge());
           preparedStatement.setString(3,person1.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(int id, Person1 personUpdated) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name =?,age = ?,email = ? WHERE id = ?");

            preparedStatement.setString(1,personUpdated.getName());
            preparedStatement.setInt(2,personUpdated.getAge());
            preparedStatement.setString(3,personUpdated.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try { preparedStatement =
                     connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
