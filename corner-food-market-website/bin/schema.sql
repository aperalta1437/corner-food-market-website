CREATE TABLE CUSTOMER(
    ID SMALLSERIAL,
    EMAIL VARCHAR(254) NOT NULL UNIQUE,
    PASSWORD VARCHAR(64) NOT NULL,
    FIRST_NAME VARCHAR(25) NOT NULL,
    MIDDLE_NAME VARCHAR(25),
    LAST_NAME VARCHAR(35) NOT NULL,
    CELL_PHONE_NUMBER VARCHAR(15),
    IS_DISABLED BOOLEAN NOT NULL,
    TOTAL_CART_ITEMS SMALLINT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);

CREATE TYPE TFA_TYPE AS ENUM ('SMS', 'EMAIL');

CREATE TABLE ADMINISTRATOR(
    ID SMALLSERIAL,
    EMAIL VARCHAR(254) NOT NULL UNIQUE,
    PASSWORD VARCHAR(64) NOT NULL,
    FIRST_NAME VARCHAR(25) NOT NULL,
    MIDDLE_NAME VARCHAR(25),
    LAST_NAME VARCHAR(35) NOT NULL,
    CELL_PHONE_NUMBER VARCHAR(15),
    IS_DISABLED BOOLEAN NOT NULL,
    IS_TFA_ENABLED BOOLEAN NOT NULL,
    TFA_CODE VARCHAR(64),
    TFA_EXPIRATION_TIME TIMESTAMP WITHOUT TIME ZONE,
    TFA_CHOSEN_TYPE TFA_TYPE,
    RSA_PRIVATE_KEY TEXT,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);

CREATE TABLE NEW_ADMINISTRATOR_REQUEST(
    ID SMALLSERIAL,
    EMAIL VARCHAR(254) NOT NULL,
    CELL_PHONE_NUMBER VARCHAR(15),
    UUID VARCHAR(64) NOT NULL UNIQUE,
    EXPIRATION_DATETIME TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    IS_CANCELLED BOOLEAN NOT NULL,
    IS_USED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    CANCELLED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);



CREATE TABLE DELIVERY_ADDRESS(
    ID SERIAL,
    CUSTOMER_ID SMALLINT NOT NULL,
    ADDRESS_LINE_1 VARCHAR(50) NOT NULL,
    ADDRESS_LINE_2 VARCHAR(25),
    CITY VARCHAR(35) NOT NULL,
    STATE_CODE CHAR(2),
    POSTAL_CODE VARCHAR(10) NOT NULL,
    COUNTRY_ALPHA2_CODE CHAR(2) NOT NULL,
    TELEPHONE_NUMBER VARCHAR(15),
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID)
--  CONSTRAINT FK_COUNTRY_CODE
--      FOREIGN KEY(COUNTRY_ALPHA2_CODE)
--          REFERENCES COUNTRY_CODE(ALPHA2_CODE)
);

--CREATE TABLE COUNTRY_CODE(
--  ID SMALLSERIAL,
--  ALPHA2_CODE CHAR(2) NOT NULL UNIQUE,
--  ALPHA3_CODE CHAR(3) NOT NULL UNIQUE,
--  NUMERIC_CODE CHAR(3) NOT NULL UNIQUE,
--  COUNTRY_NAME VARCHAR(50) NOT NULL,
--  PRIMARY KEY(ID)
--);

CREATE TABLE ITEM_INVENTORY(
    ID SMALLSERIAL,
    QUANTITY SMALLINT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);

CREATE TABLE ITEM_CATEGORY(
    ID SMALLSERIAL,
    NAME VARCHAR(25) NOT NULL UNIQUE,
    DESCRIPTION TEXT NOT NULL,
    PARENT_CATEGORY_ID SMALLINT,
    CATEGORY_LEVEL SMALLINT NOT NULL,
    URL_ROUTE_NAME VARCHAR(25) NOT NULL UNIQUE,
    IS_LEAF BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_SELF
      FOREIGN KEY(PARENT_CATEGORY_ID)
          REFERENCES ITEM_CATEGORY(ID)
);

CREATE TABLE ITEM(
    ID SMALLSERIAL,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    SKU VARCHAR(12) NOT NULL UNIQUE,
    CATEGORY_ID SMALLINT NOT NULL,
    INVENTORY_ID SMALLINT NOT NULL,
    PRICE DECIMAL NOT NULL,
    IS_ON_SALE BOOLEAN NOT NULL,
    IS_POPULAR BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ITEM_CATEGORY
        FOREIGN KEY(CATEGORY_ID)
            REFERENCES ITEM_CATEGORY(ID),
    CONSTRAINT FK_ITEM_INVENTORY
        FOREIGN KEY(INVENTORY_ID)
            REFERENCES ITEM_INVENTORY(ID)
);

CREATE TYPE DISCOUNT_TYPE_NAME AS ENUM ('BUY_AND_GET_FREE', 'CONTRACTUAL', 'FREE_SHIPPING');

CREATE TABLE DISCOUNT_TYPE(
    ID SMALLSERIAL,
    NAME DISCOUNT_TYPE_NAME NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    PUBLIC_DESCRIPTION_TEMPLATE TEXT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);

CREATE TABLE DISCOUNT_HISTORY(
    ID BIGSERIAL,
    ITEM_ID SMALLINT NOT NULL,
    DISCOUNT_TYPE_ID SMALLINT NOT NULL,
    IS_QUANTITY_BASED BOOLEAN NOT NULL,
    QUANTITY_FOR_DISCOUNT SMALLINT,
    QUANTITY_FROM_DISCOUNT SMALLINT,
    IS_PERCENTAGE_BASED BOOLEAN NOT NULL,
    IS_AMOUNT_BASED BOOLEAN NOT NULL,
    DISCOUNT_PERCENT DECIMAL,
    DISCOUNT_AMOUNT DECIMAL,
    IS_ACTIVE BOOLEAN NOT NULL,
    MINIMUM_CUSTOMER_EXPENDITURE DECIMAL,
    MAXIMUM_EXPENDITURE_TIMEFRAME BIGINT,
    EFFECTIVE_DATETIME TIMESTAMP WITH TIME ZONE,
    EXPIRATION_DATETIME TIMESTAMP WITH TIME ZONE,
    IS_REPEATED BOOLEAN NOT NULL,
    REPEATED_AFTER_TIMEFRAME BIGINT,
    TOTAL_REPETITIONS SMALLINT,
    WAS_DELETED BOOLEAN NOT NULL,
    WAS_MODIFIED BOOLEAN NOT NULL,
    MODIFIED_DISCOUNT_HISTORY_ID BIGINT,
    CREATED_BY_ADMINISTRATOR_ID SMALLINT NOT NULL,
    MODIFIED_BY_ADMINISTRATOR_ID SMALLINT,
    DELETED_BY_ADMINISTRATOR_ID SMALLINT,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID),
    CONSTRAINT FK_CREATED_BY_ADMINISTRATOR
        FOREIGN KEY(CREATED_BY_ADMINISTRATOR_ID)
            REFERENCES ADMINISTRATOR(ID),
    CONSTRAINT FK_MODIFIED_BY_ADMINISTRATOR
        FOREIGN KEY(MODIFIED_BY_ADMINISTRATOR_ID)
            REFERENCES ADMINISTRATOR(ID),
    CONSTRAINT FK_DELETED_BY_ADMINISTRATOR
        FOREIGN KEY(DELETED_BY_ADMINISTRATOR_ID)
            REFERENCES ADMINISTRATOR(ID),
    CONSTRAINT FK_MODIFIED_DISCOUNT_HISTORY
        FOREIGN KEY(MODIFIED_DISCOUNT_HISTORY_ID)
            REFERENCES DISCOUNT_HISTORY(ID)
);

CREATE TABLE DISCOUNT(
    ID SERIAL,
    ITEM_ID SMALLINT NOT NULL,
    DISCOUNT_TYPE_ID SMALLINT NOT NULL,
    IS_QUANTITY_BASED BOOLEAN NOT NULL,
    QUANTITY_FOR_DISCOUNT SMALLINT,
    QUANTITY_FROM_DISCOUNT SMALLINT,
    IS_PERCENTAGE_BASED BOOLEAN NOT NULL,
    IS_AMOUNT_BASED BOOLEAN NOT NULL,
    DISCOUNT_PERCENT DECIMAL,
    DISCOUNT_AMOUNT DECIMAL,
    IS_ACTIVE BOOLEAN NOT NULL,
    MINIMUM_CUSTOMER_EXPENDITURE DECIMAL,
    MAXIMUM_EXPENDITURE_TIMEFRAME BIGINT,
    EFFECTIVE_DATETIME TIMESTAMP WITH TIME ZONE,
    EXPIRATION_DATETIME TIMESTAMP WITH TIME ZONE,
    IS_REPEATED BOOLEAN NOT NULL,
    REPEATED_AFTER_TIMEFRAME BIGINT,
    TOTAL_REPETITIONS SMALLINT,
    IS_MODIFIED BOOLEAN NOT NULL,
    DISCOUNT_HISTORY_ID BIGINT NOT NULL,
    CREATED_BY_ADMINISTRATOR_ID SMALLINT NOT NULL,
    MODIFIED_BY_ADMINISTRATOR_ID SMALLINT,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID),
    CONSTRAINT FK_CREATED_BY_ADMINISTRATOR
        FOREIGN KEY(CREATED_BY_ADMINISTRATOR_ID)
            REFERENCES ADMINISTRATOR(ID),
    CONSTRAINT FK_MODIFIED_BY_ADMINISTRATOR
        FOREIGN KEY(MODIFIED_BY_ADMINISTRATOR_ID)
            REFERENCES ADMINISTRATOR(ID),
    CONSTRAINT FK_DISCOUNT_HISTORY
        FOREIGN KEY(DISCOUNT_HISTORY_ID)
            REFERENCES DISCOUNT_HISTORY(ID)
);

CREATE TABLE FILE_TYPE(
    ID SMALLSERIAL,
    NAME VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPTION TEXT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);

CREATE TABLE FILE_RELATIVE_PATH(
    ID SMALLSERIAL,
    FILE_TYPE_ID SMALLINT NOT NULL,
    RELATIVE_PATH VARCHAR(200) NOT NULL UNIQUE,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_FILE_TYPE
          FOREIGN KEY(FILE_TYPE_ID)
              REFERENCES FILE_TYPE(ID)
);


-- A separate table for the images is created to incorporate multiple images per item for single-item-view.
CREATE TABLE ITEM_IMAGE(
    ID SERIAL,
    ITEM_ID SMALLINT NOT NULL,
    FILE_EXTENSION VARCHAR(5) NOT NULL,
    FILE_NAME VARCHAR(50) NOT NULL,
    SORT_NUMBER SMALLINT NOT NULL,
    FILE_RELATIVE_PATH_ID SMALLINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID),
    CONSTRAINT FK_FILE_RELATIVE_PATH
        FOREIGN KEY(FILE_RELATIVE_PATH_ID)
            REFERENCES FILE_RELATIVE_PATH(ID)
);


CREATE TABLE PAYMENT_DETAILS(
    ID SERIAL,
    AMOUNT DECIMAL NOT NULL,
    PROVIDER_ID SMALLINT NOT NULL,
    STATUS VARCHAR(25) NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID)
);


CREATE TABLE ORDER_DETAILS(
    ID SERIAL,
    ORDER_NUMBER INTEGER NOT NULL UNIQUE,
    CUSTOMER_ID SMALLINT NOT NULL,
    FIRST_PAYMENT_ID INTEGER,
    SECOND_PAYMENT_ID INTEGER,
    THIRD_PAYMENT_ID INTEGER,
    SUBTOTAL DECIMAL NOT NULL,
    TOTAL DECIMAL NOT NULL,
    TAXES DECIMAL NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID),
    CONSTRAINT FK_FIRST_PAYMENT_DETAILS
        FOREIGN KEY(FIRST_PAYMENT_ID)
            REFERENCES PAYMENT_DETAILS(ID),
    CONSTRAINT FK_SECOND_PAYMENT_DETAILS
        FOREIGN KEY(SECOND_PAYMENT_ID)
            REFERENCES PAYMENT_DETAILS(ID),
    CONSTRAINT FK_THIRD_PAYMENT_DETAILS
        FOREIGN KEY(THIRD_PAYMENT_ID)
            REFERENCES PAYMENT_DETAILS(ID)
);


CREATE TABLE ORDER_ITEM(
    ID SERIAL,
    ORDER_ID INTEGER NOT NULL,
    ITEM_ID SMALLINT NOT NULL,
    QUANTITY SMALLINT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_ORDER_DETAILS
        FOREIGN KEY(ORDER_ID)
            REFERENCES ORDER_DETAILS(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID)
);

CREATE TABLE SHOPPING_SESSION(
    CUSTOMER_ID SMALLINT,
    SUBTOTAL DECIMAL NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(CUSTOMER_ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID)
);

CREATE TABLE CART_ITEM(
    CUSTOMER_ID SMALLINT,
    ITEM_ID SMALLINT,
    IN_CART_QUANTITY SMALLINT,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(CUSTOMER_ID, ITEM_ID),
    --  CONSTRAINT FK_SHOPPING_SESSION
    --      FOREIGN KEY(CUSTOMER_ID)
    --          REFERENCES SHOPPING_SESSION(CUSTOMER_ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID)
);


CREATE TABLE GENERAL_REVIEW(
    ID SERIAL,
    CUSTOMER_ID SMALLINT NOT NULL,
    IS_ANONYMOUS_TO_CUSTOMERS BOOLEAN NOT NULL,
    IS_ANONYMOUS_TO_EVERYONE BOOLEAN NOT NULL,
    IS_REGISTERED_USER BOOLEAN NOT NULL,
    IS_ACTIVE_SHOPPER BOOLEAN NOT NULL,
    SUBJECT_LINE VARCHAR(50),
    COMMENT TEXT NOT NULL,
    STAR_RATING SMALLINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID)
);

CREATE TABLE ORDER_REVIEW(
    ID SERIAL,
    CUSTOMER_ID SMALLINT NOT NULL,
    ORDER_ID SMALLINT,
    IS_ANONYMOUS_TO_CUSTOMERS BOOLEAN NOT NULL,
    IS_ANONYMOUS_TO_EVERYONE BOOLEAN NOT NULL,
    SUBJECT_LINE VARCHAR(50),
    COMMENT TEXT NOT NULL,
    STAR_RATING SMALLINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID),
    CONSTRAINT FK_ORDER_DETAILS
        FOREIGN KEY(ORDER_ID)
            REFERENCES ORDER_DETAILS(ID)
);

CREATE TABLE ITEM_REVIEW(
    ID SERIAL,
    CUSTOMER_ID SMALLINT NOT NULL,
    ITEM_ID SMALLINT NOT NULL,
    IS_ANONYMOUS_TO_CUSTOMERS BOOLEAN NOT NULL,
    IS_ANONYMOUS_TO_EVERYONE BOOLEAN NOT NULL,
    IS_REGISTERED_USER BOOLEAN NOT NULL,
    IS_ACTIVE_SHOPPER BOOLEAN NOT NULL,
    SUBJECT_LINE VARCHAR(50),
    COMMENT TEXT NOT NULL,
    STAR_RATING SMALLINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_CUSTOMER
        FOREIGN KEY(CUSTOMER_ID)
            REFERENCES CUSTOMER(ID),
    CONSTRAINT FK_ITEM
        FOREIGN KEY(ITEM_ID)
            REFERENCES ITEM(ID)
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

--ALTER TABLE RESERVATION ADD FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID);
--ALTER TABLE RESERVATION ADD FOREIGN KEY (GUEST_ID) REFERENCES GUEST(GUEST_ID);
--CREATE INDEX IDX_RES_DATE_ ON RESERVATION(RES_DATE);

CREATE TABLE EXCEPTION_LOG(
    ID BIGSERIAL,
    STACK_TRACE TEXT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE BANNER_IMAGE(
    ID SERIAL,
    DISCOUNT_ID INTEGER,
    FILE_EXTENSION VARCHAR(5) NOT NULL,
    FILE_NAME VARCHAR(50) NOT NULL,
    SORT_NUMBER SMALLINT NOT NULL,
    FILE_RELATIVE_PATH_ID SMALLINT NOT NULL,
    HAS_TEXT_OVERLAY BOOLEAN NOT NULL,
    TEXT_OVERLAY_1 VARCHAR(25),
    TEXT_OVERLAY_1_CSS_LEFT_VALUE VARCHAR(5),
    TEXT_OVERLAY_1_CSS_TOP_VALUE VARCHAR(5),
    TEXT_OVERLAY_2 VARCHAR(25),
    TEXT_OVERLAY_2_CSS_LEFT_VALUE VARCHAR(5),
    TEXT_OVERLAY_2_CSS_TOP_VALUE VARCHAR(5),
    IS_DISABLED BOOLEAN NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL,
    MODIFIED_AT TIMESTAMP WITH TIME ZONE,
    DISABLED_AT TIMESTAMP WITH TIME ZONE,
    DELETED_AT TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(ID),
    CONSTRAINT FK_DISCOUNT
        FOREIGN KEY(DISCOUNT_ID)
            REFERENCES DISCOUNT(ID),
    CONSTRAINT FK_FILE_RELATIVE_PATH
        FOREIGN KEY(FILE_RELATIVE_PATH_ID)
            REFERENCES FILE_RELATIVE_PATH(ID)
);

