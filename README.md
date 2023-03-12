# Итоговая аттестация

### Организуйте систему учёта для питомника, в котором живут домашние и вьючные животные.

1. Используя команду cat в терминале операционной системы Linux, создать два файла 
    Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными
    заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. 
    ```shell
        $ cat > pets.txt
        $ cat > pack_animals.txt
        $ cat pets.txt pack_animals.txt > all_together.txt
    ```
    ![1.png](img/1.png)

    
    Просмотреть содержимое созданного файла.
    Переименовать файл, дав ему новое имя (Друзья человека).

    ```shell
        $ cat all_animals.txt
        $ mv all_animals.txt human_friends.txt
    ```
    
    ![2.png](img/2.png)

2. Создать директорию, переместить файл туда
    ```shell
        $ mkdir animals
        $ mv human_friends.txt animals/
    ```

    ![222.png](img/222.png)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет
   из этого репозитория.
    ```shell
        $ sudo dpkg -i mysql-apt-config_0.8.24-1_all.deb
        $ sudo apt update 
    ```
   
   
   ```shell
      $ sudo apt install mysql-server mysql-client
      $ systemctl status mysql.service
   ```
   ![3_1.png](img/3_1.png)

4. Установить и удалить deb-пакет с помощью dpkg.

   ```shell
      $ sudo dpkg -i mysql-apt-config_0.8.24-1_all.deb
      $ sudo dpkg -r mysql-apt-config
      $ sudo dpkg --purge mysql-apt-config
   ```
5. Нарисовать диаграмму, в которой есть класс родительский класс, домашние 
   животные и вьючные животные, в составы которых в случае домашних
   животных войдут классы: собаки, кошки, хомяки, 
   а в класс вьючные животные войдут: Лошади, верблюды и ослы).
   #### Диаграмма классов.   

   ![ClassDiagram.jpg](img/ClassDiagram.jpg)
   
   #### ERD - диаграмма

   ![ERD.jpg](img/ERD.jpg)
   
6. В подключенном MySQL репозитории создать базу данных “Друзья человека”

   ```sql
      CREATE DATABASE IF NOT EXISTS HumanFriends;
      USE HumanFriends;
   ```

7. Создать таблицы с иерархией из диаграммы в БД

   ```sql
      CREATE TABLE `AnimalNursery` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `age` INT NOT NULL,
      `genus_id` INT NOT NULL,
      PRIMARY KEY (`id`)
   );

   CREATE TABLE `AnimalGenuses` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `group_id` INT NOT NULL,
      `name` varchar(30) NOT NULL AUTO_INCREMENT,
      PRIMARY KEY (`id`)
   );

   CREATE TABLE `AnimalGroup` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `name` varchar(255) NOT NULL,
      PRIMARY KEY (`id`)
   );

   CREATE TABLE `AnimalCommands` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `animal_id` INT NOT NULL,
      `command_id` INT NOT NULL,
      PRIMARY KEY (`id`)
   );

   CREATE TABLE `Commands` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `note` VARCHAR(255) NOT NULL,
      PRIMARY KEY (`id`)
   );

   ALTER TABLE `AnimalNursery` ADD CONSTRAINT `AnimalNursery_fk0` FOREIGN KEY (`genus_id`) REFERENCES `AnimalGenuses`(`id`);

   ALTER TABLE `AnimalGenuses` ADD CONSTRAINT `AnimalGenuses_fk0` FOREIGN KEY (`group_id`) REFERENCES `AnimalGroup`(`id`);

   ALTER TABLE `AnimalCommands` ADD CONSTRAINT `AnimalCommands_fk0` FOREIGN KEY (`animal_id`) REFERENCES `AnimalNursery`(`id`);

   ALTER TABLE `AnimalCommands` ADD CONSTRAINT `AnimalCommands_fk1` FOREIGN KEY (`command_id`) REFERENCES `Commands`(`id`);
   ```
8. Заполнить низкоуровневые таблицы именами (животных), командами которые они выполняют и датами рождения
   
   ```sql
    USE HumanFriends;

   INSERT INTO Commands(name)
   VALUES
    ('Принести тапки'),
	('Бегать в колесе'),
	('Апорт'),
	('Сидеть'),
	('Голос');
   
   INSERT INTO AnimalGroup (name)
   VALUES
    ('Вьючные животные'),
    ('Домашние животные');
   
   INSERT INTO AnimalGenuses (name, group_id)
   VALUES
    ('Лошадь', 1),
    ('Верблюд', 1),
    ('Осел', 1),
    ('Кошка', 2),
    ('Собака', 2),
    ('Хомяк', 2);
   
   INSERT INTO AnimalNursery (name, age, genius_id)
   VALUES
    ('Гнедой', 6, 1),
	('Гнедой_2', 7, 1),
	('Тупица', 2, 3),
	('Рыжий', 3, 2),
	('Песик', 4, 5),
	('Хомяк', 2, 6),
	('Эльза', 4, 4);
   
   INSERT INTO AnimalCommands (animal_id, command_id)
   VALUES
    (1, 3), (2, 3), (2, 4), (3, 4),
    (4, 5), (5, 1), (5, 4), (6, 2),
    (7, 1);
   ```
9. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
   питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
   ```sql
      USE HumanFriends;
      DELETE FROM AnimalNursery WHERE genus_id = 2;
   
      CREATE TABLE HorseAndDonkey AS
	  SELECT * from AnimalNursery WHERE genus_id = 1
      UNION
      SELECT * from AnimalNursery WHERE genus_id = 3;
   ```
10. Создать новую таблицу “молодые животные” в которую попадут все
    животные старше 1 года, но младше 3 лет

   ```sql
      CREATE TABLE YoungAnimals AS
         SELECT id, name, age 
         WHERE age BETWEEN 1 and 3 ;
   ```
11. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
    прошлую принадлежность к старым таблицам.
   ```sql
      SELECT id, name, age, genus_id FROM HorseDonkey
      UNION
      SELECT id, name, age, genus_id FROM YoungAnimals;
   ```# AnimalsNusrsery_FinalTest
# AnimalNursery
