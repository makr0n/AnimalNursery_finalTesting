package model.implement;

import model.AbstractPackAnimal;
import model.AnimalGenus;

import java.time.LocalDate;

public class Donkey extends AbstractPackAnimal {
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenus.DONKEY);
    }
}
