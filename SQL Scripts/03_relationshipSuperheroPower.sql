CREATE TABLE Hero_to_power();
ALTER TABLE Hero_to_power ADD COLUMN Hero_Id int REFERENCES Superhero;
ALTER TABLE Hero_to_power ADD COLUMN Power_Id int REFERENCES Power;


