package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonRepository {
    private final static Map<Long, Person> storage = new HashMap<>();

    public static Optional<Person> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public static void save(Person person) {
        storage.put(person.id(), person);
    }

    public static Optional<Person> findByName(String name) {
        return storage.values().stream().filter(person -> person.name().equals(name)).findFirst();
    }

    public static List<Person> findByFavouriteWeekday(DaysOfWeek dayOfWeek) {
        return storage.values().stream().filter(person -> person.favouriteDay() == dayOfWeek).toList();
    }

    public static long countByGender(Gender gender) {
        return storage.values().stream().filter(person -> person.gender() == gender).count();
    }
}
