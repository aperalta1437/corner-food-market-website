CREATE TABLE CUSTOMER(
  ID SMALLSERIAL,
  USERNAME VARCHAR(35) NOT NULL UNIQUE,
  PASSWORD VARCHAR(50) NOT NULL,
  FIRST_NAME VARCHAR(25) NOT NULL,
  LAST_NAME VARCHAR(35) NOT NULL,
  TELEPHONE VARCHAR(15),
  CREATED_AT TIMESTAMP WITHOUT TIME ZONE,
  MODIFIED_AT TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY(ID)
);

CREATE TABLE CUSTOMER_ADDRESS(
  ID SERIAL,
  CUSTOMER_ID SMALLINT NOT NULL,
  ADDRESS_LINE1 VARCHAR(50) NOT NULL,
  ADDRESS_LINE2 VARCHAR(25),
  CITY VARCHAR(35) NOT NULL,
  POSTAL_CODE VARCHAR(10) NOT NULL,
  COUNTRY VARCHAR(35) NOT NULL,
  TELEPHONE VARCHAR(15),
  CREATED_AT TIMESTAMP WITHOUT TIME ZONE,
  MODIFIED_AT TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY(ID),
  CONSTRAINT FK_USER
    FOREIGN KEY(CUSTOMER_ID)
        REFERENCES CUSTOMER(ID)
);

--CREATE TABLE USER_PAYMENT(
--  ID SERIAL,
--  USER_ID SMALLINT NOT NULL,
--  PAYMENT_TYPE_ID SMALLINT NOT NULL,
--  PROVIDER VARCHAR(25),
--  ACCOUNT_NO VARCHAR(16) NOT NULL,
--  POSTAL_CODE VARCHAR(10) NOT NULL,
--  COUNTRY VARCHAR(35) NOT NULL,
--  TELEPHONE VARCHAR(15),
--  CREATED_AT TIMESTAMP WITHOUT TIME ZONE,
--  MODIFIED_AT TIMESTAMP WITHOUT TIME ZONE
--  PRIMARY KEY(ID)
--  CONSTRAINT FK_USER
--    FOREIGN KEY(USER_ID)
--        REFERENCES USER(ID)
--);
--
--
--
--
--CREATE TABLE GUEST(
--  GUEST_ID BIGSERIAL PRIMARY KEY,
--  FIRST_NAME VARCHAR(64),
--  LAST_NAME VARCHAR(64),
--  EMAIL_ADDRESS VARCHAR(64),
--  ADDRESS VARCHAR(64),
--  COUNTRY VARCHAR(32),
--  STATE VARCHAR(12),
--  PHONE_NUMBER VARCHAR(24)
--);
--
--CREATE TABLE RESERVATION(
--  RESERVATION_ID BIGSERIAL PRIMARY KEY,
--  ROOM_ID BIGINT NOT NULL,
--  GUEST_ID BIGINT NOT NULL,
--  RES_DATE DATE
--);
--
--ALTER TABLE RESERVATION ADD FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID);
--ALTER TABLE RESERVATION ADD FOREIGN KEY (GUEST_ID) REFERENCES GUEST(GUEST_ID);
--CREATE INDEX IDX_RES_DATE_ ON RESERVATION(RES_DATE);