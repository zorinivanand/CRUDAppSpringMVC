package ru.zorinivanand.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zorinivanand.models.Person1;

import java.util.ArrayList;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private ArrayList<Person1> people;
    {
        people = new ArrayList<>();

        people.add(new Person1(++PEOPLE_COUNT,"aaa"));
        people.add(new Person1(++PEOPLE_COUNT,"bbb"));
        people.add(new Person1(++PEOPLE_COUNT,"ccc"));

    }

    public ArrayList<Person1>index(){
        return people;
    }

    public Person1 show(final int id){
        return people.stream().filter(Person1 -> Person1.getId() ==id).findAny().orElse(null);
    }
}
