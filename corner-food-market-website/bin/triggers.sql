-- CUSTOMER ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_CUSTOMER() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON CUSTOMER
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_CUSTOMER();

-- DELIVERY_ADDRESS ------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_DELIVERY_ADDRESS() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON DELIVERY_ADDRESS
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_DELIVERY_ADDRESS();

-- ITEM_CATEGORY --------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_ITEM_CATEGORY() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON ITEM_CATEGORY
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_ITEM_CATEGORY();



CREATE OR REPLACE FUNCTION set_parent_and_child_IS_LEAF_for_ITEM_CATEGORY() RETURNS TRIGGER AS $BODY$
DECLARE
BEGIN
    NEW.IS_LEAF = TRUE;
    IF (NEW.PARENT_CATEGORY_ID IS NOT NULL) THEN
        UPDATE ITEM_CATEGORY SET IS_LEAF = FALSE WHERE ID = NEW.PARENT_CATEGORY_ID;
    END IF;
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_parent_and_child_IS_LEAF_on_before_INSERT
BEFORE INSERT ON ITEM_CATEGORY
FOR EACH ROW
EXECUTE PROCEDURE set_parent_and_child_IS_LEAF_for_ITEM_CATEGORY();

-- ITEM_INVENTORY --------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_ITEM_INVENTORY() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON ITEM_INVENTORY
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_ITEM_INVENTORY();


-- ITEM ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_ITEM() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON ITEM
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_ITEM();


-- ITEM_IMAGE ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_ITEM_IMAGE() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON ITEM_IMAGE
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_ITEM_IMAGE();


CREATE OR REPLACE FUNCTION set_FILE_NAME_for_ITEM_IMAGE() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.FILE_NAME = CONCAT(CAST(NEW.FILE_NAME_PREFIX AS VARCHAR), '.', NEW.FILE_EXTENSION);
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_FILE_NAME_on_before_INSERT
BEFORE INSERT ON ITEM_IMAGE
FOR EACH ROW
EXECUTE PROCEDURE set_FILE_NAME_for_ITEM_IMAGE();


-- FILE_TYPE ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_FILE_TYPE() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON FILE_TYPE
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_FILE_TYPE();


-- FILE_RELATIVE_PATH ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_FILE_RELATIVE_PATH() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON FILE_RELATIVE_PATH
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_FILE_RELATIVE_PATH();


-- GENERAL_REVIEW ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_GENERAL_REVIEW() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON GENERAL_REVIEW
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_GENERAL_REVIEW();


-- CART_ITEM ----------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_CART_ITEM() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON CART_ITEM
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_CART_ITEM();


-- ADMINISTRATOR ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_ADMINISTRATOR() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON ADMINISTRATOR
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_ADMINISTRATOR();


-- DISCOUNT ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_DISCOUNT() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON DISCOUNT
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_DISCOUNT();


-- DISCOUNT_HISTORY ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_DISCOUNT_HISTORY() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON DISCOUNT_HISTORY
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_DISCOUNT_HISTORY();


-- DISCOUNT_TYPE ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_DISCOUNT_TYPE() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON DISCOUNT_TYPE
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_DISCOUNT_TYPE();


-- NEW_ADMINISTRATOR_REQUEST ---------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION set_CREATED_AT_for_NEW_ADMINISTRATOR_REQUEST() RETURNS TRIGGER AS $BODY$
BEGIN
    NEW.CREATED_AT = now();
    RETURN NEW;
END; $BODY$
LANGUAGE plpgsql;

CREATE TRIGGER set_CREATED_AT_on_before_INSERT
BEFORE INSERT ON NEW_ADMINISTRATOR_REQUEST
FOR EACH ROW
EXECUTE PROCEDURE set_CREATED_AT_for_NEW_ADMINISTRATOR_REQUEST();

--CREATE OR REPLACE FUNCTION set_CREATED_AT_for_CUSTOMER() RETURNS TRIGGER AS $$
--BEGIN
--    UPDATE  CUSTOMER
--    SET     CREATED_AT = now()
--    WHERE   ID = NEW.ID;
--
--    RETURN NEW;
--END; $$
--LANGUAGE plpgsql;
--
--CREATE TRIGGER set_CREATED_AT_on_after_INSERT
--AFTER INSERT ON CUSTOMER
--FOR EACH ROW
--EXECUTE PROCEDURE set_CREATED_AT_for_CUSTOMER();