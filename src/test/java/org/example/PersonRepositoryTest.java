package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class PersonRepositoryTest {

    @BeforeAll
    static void initialize() {
        Person alice = new Person(1, "Alice", Gender.FEMALE, DaysOfWeek.SATURDAY);
        Person bob = new Person(2, "Bob", Gender.MALE, DaysOfWeek.SATURDAY);
        Person charlie = new Person(3, "Charlie", Gender.MALE, DaysOfWeek.SUNDAY);
        PersonRepository.save(alice);
        PersonRepository.save(bob);
        PersonRepository.save(charlie);
    }

    @Test
    void findByIdTest_exists() {
        Person alice = PersonRepository.findById(1).orElse(null);

        Assertions.assertNotNull(alice);
        Assertions.assertEquals(1, alice.id());
        Assertions.assertEquals("Alice", alice.name());
        Assertions.assertEquals(Gender.FEMALE, alice.gender());
        Assertions.assertEquals(DaysOfWeek.SATURDAY, alice.favouriteDay());

    }

    @Test
    void findByIdTest_notExists() {
        Person notExists = PersonRepository.findById(4).orElse(null);

        Assertions.assertNull(notExists);
    }

    @Test
    void findByName_exists() {
        Person alice = PersonRepository.findByName("Alice").orElse(null);

        Assertions.assertNotNull(alice);
        Assertions.assertEquals(1, alice.id());
        Assertions.assertEquals("Alice", alice.name());
        Assertions.assertEquals(Gender.FEMALE, alice.gender());
        Assertions.assertEquals(DaysOfWeek.SATURDAY, alice.favouriteDay());
    }

    @Test
    void findByName_notExists() {
        Person notExists = PersonRepository.findByName("Diana").orElse(null);

        Assertions.assertNull(notExists);
    }

    @Test
    void findByFavouriteWeekday_found() {
        List<Person> persons = PersonRepository.findByFavouriteWeekday(DaysOfWeek.SATURDAY);

        Assertions.assertEquals(2, persons.size());
    }

    @Test
    void findByFavouriteWeekday_notFound() {
        List<Person> persons = PersonRepository.findByFavouriteWeekday(DaysOfWeek.MONDAY);

        Assertions.assertEquals(0, persons.size());
    }

    @Test
    void countByGender_exists() {
        long maleCount = PersonRepository.countByGender(Gender.MALE);

        Assertions.assertEquals(2, maleCount);
    }

    @Test
    void countByGender_notExists() {
        long diverseCount = PersonRepository.countByGender(Gender.DIVERSE);

        Assertions.assertEquals(0, diverseCount);
    }
}