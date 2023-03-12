package model.implement;

import model.AbstractPet;
import model.AnimalGenus;

import java.time.LocalDate;

public class Hamster extends AbstractPet {
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.HAMSTER);
    }
}
