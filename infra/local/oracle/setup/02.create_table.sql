connect test_user/testpassword@ORCLPDB1
CREATE TABLE users (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    age NUMBER NOT NULL
);