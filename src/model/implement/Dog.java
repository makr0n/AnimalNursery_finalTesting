package model.implement;

import model.AbstractPet;
import model.AnimalGenus;

import java.time.LocalDate;

public class Dog extends AbstractPet {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.DOG);
    }
}
