drop table if exists HOLIDAY;


CREATE TABLE HOLIDAY (
    ID BIGINT AUTO_INCREMENT,
    DATENAME VARCHAR(50) NOT NULL,
    MONTH SMALLINT NOT NULL,
    DAY SMALLINT NOT NULL,
    ISLUNAR boolean NOT NULL,
    PRIMARY KEY (ID)
);


INSERT INTO HOLIDAY (DATENAME, MONTH, DAY, ISLUNAR) 
VALUES
   ('1월 1일', 1, 1, false), 
   ('3·1절', 3, 1, false),
   ('광복절', 8, 15, false),
   ('개천절', 10, 3, false),
   ('한글날', 10, 9, false),
    ('어린이날', 5, 5, false),
   ('현충일', 6, 6, false),
    ('기독탄신일', 12, 25, false),
   ('석가탄신일', 4, 8, true),
   ('설날', 1, 1, true),
   ('설날', 1, 2, true),
   ('설날', 12, 30, true),
    ('추석', 8, 14, true),
   ('추석', 8, 15, true),
   ('추석', 8, 16, true);