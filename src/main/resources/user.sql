CREATE TABLE user (id int NOT NULL, name varchar(30), email varchar(40), PRIMARY KEY (id));


        CREATE TRIGGER avoid_empty_update
            BEFORE UPDATE
            ON user
            FOR EACH ROW
        BEGIN
            IF NEW.name = '' AND NEW.email = '' THEN
                SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Fields cannot be blank/empty';
            END IF;
        END;