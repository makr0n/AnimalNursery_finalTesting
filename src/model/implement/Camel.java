package model.implement;

import model.AbstractPackAnimal;
import model.AnimalGenus;

import java.time.LocalDate;

public class Camel extends AbstractPackAnimal {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.CAMEL);
    }
}
