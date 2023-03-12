package view;

import model.AbstractAnimal;

public interface View {

    enum MainMenuCommand {
        ADD_ANIMAL ("Добавить новое животное"),
        SHOW_SKILLS("Показать все команды"),
        REMOVE_ANIMAL ("Выписать животное"),
        EXIT ("Выйти");

        private String tag;

        MainMenuCommand(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    enum AddSkillMenuCommand {
        ADD_SKILL ("Обучение новой команде"),
        EXIT ("Выйти");

        private String tag;

        AddSkillMenuCommand(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }


    void showKennelRegistry();


    MainMenuCommand showMainMenuWithResult();


    boolean showAddAnimalDialog();

    int showRemoveAnimalDialog();


    void showDetailInfoAnimalDialog();


    void showAnimalInfo(AbstractAnimal animal);

    AddSkillMenuCommand showAddSkillMenu(AbstractAnimal animal);

    boolean showAddSkillDialog(AbstractAnimal animal);
}
