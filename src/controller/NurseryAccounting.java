package controller;

import model.AbstractAnimal;
import model.AnimalGenus;
import model.implement.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;



public class NurseryAccounting {

    private Storage dbNursery;

    public NurseryAccounting(Storage dbNursery) {
        this.dbNursery = dbNursery;
    }

    public List<AbstractAnimal> getAnimals() {
        return dbNursery.getAllAnimals();
    }

    public boolean createAnimal(String name, LocalDate birthDay, AnimalGenus animalGenus) {
        AbstractAnimal animal = switch (animalGenus) {
            case CAT -> new Cat(name, birthDay);
            case DOG -> new Dog(name, birthDay);
            case HAMSTER -> new Hamster(name, birthDay);
            case HORSE -> new Horse(name, birthDay);
            case CAMEL -> new Camel(name, birthDay);
            case DONKEY -> new Donkey(name, birthDay);
        };

        return dbNursery.addAnimal(animal);
    }

    public int removeAnimal(AbstractAnimal animal) {
        if (animal == null) return -1;
        return  dbNursery.removeAnimal(animal);
    }
}
