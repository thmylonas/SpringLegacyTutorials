package com.thomasmylonas.spring_legacy.spring_dependency_injection._models.example3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuppressWarnings(value = "all") // IntelliJ bug: "Could not autowire. No beans of X type found"
public class Teacher extends Person {

    private String bookGenre;
    private int visitsPerWeek;
    private int hoursOfDelay;

    /**
     * This constructor is an antipattern. The parameters should not be more than 5 (maybe 4).
     * In this case Builder-Pattern should be applied.
     * This constructor is written just to show that the "@AllArgsConstructor" lombok Annotation cannot be used,
     * because it does not use the super(...) method to initialize the SuperClass
     * Link:
     * - https://stackoverflow.com/questions/70990997/noargsconstructor-and-allargsconstructor-annotation-on-child-class-for-parent-co
     *
     * @param name          The Teacher's name
     * @param age           The Teacher's age
     * @param address       The Teacher's address
     * @param phoneNumber   The Teacher's phoneNumber
     * @param bookGenre     The book genre that Teacher prefers
     * @param visitsPerWeek The Teacher's visits in the library, per week
     * @param hoursOfDelay  The hours of delay that Teacher keep a book
     */
    public Teacher(String name, int age, String address, String phoneNumber,
                   String bookGenre, int visitsPerWeek, int hoursOfDelay) {
        super(name, age, address, phoneNumber);
        this.bookGenre = bookGenre;
        this.visitsPerWeek = visitsPerWeek;
        this.hoursOfDelay = hoursOfDelay;
    }
}
