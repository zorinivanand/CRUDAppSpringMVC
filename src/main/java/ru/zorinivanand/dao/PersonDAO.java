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

        people.add(new Person1(++PEOPLE_COUNT,"aaa",11,"aaa@mail.ru"));
        people.add(new Person1(++PEOPLE_COUNT,"bbb",22,"bb@mail.ru"));
        people.add(new Person1(++PEOPLE_COUNT,"ccc",33,"cc@mail.ru"));

    }

    public ArrayList<Person1>index(){
        return people;
    }

    public Person1 show(final int id){
        return people.stream().filter(Person1 -> Person1.getId() ==id).findAny().orElse(null);
    }
    public void save(Person1 person1){
        person1.setId(++PEOPLE_COUNT);
        people.add(person1);

    }

    public void update(int id, Person1 personUpdated) {
        Person1 NewPerson1 = show(id);

        NewPerson1.setName(personUpdated.getName());
        NewPerson1.setAge(personUpdated.getAge());
        NewPerson1.setEmail(personUpdated.getEmail());

    }

    public void delete(int id) {
        people.removeIf(person1 -> person1.getId() ==id);
    }
}
