package model.implement;

import model.AbstractPackAnimal;
import model.AnimalGenus;

import java.time.LocalDate;

public class Horse extends AbstractPackAnimal {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.HORSE);
    }
}
