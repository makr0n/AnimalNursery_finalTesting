package model.implement;

import model.AbstractPet;
import model.AnimalGenus;

import java.time.LocalDate;

public class Cat extends AbstractPet {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.CAT);
    }
}
