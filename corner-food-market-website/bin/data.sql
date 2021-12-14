INSERT INTO ITEM_CATEGORY (NAME, DESCRIPTION, PARENT_CATEGORY_ID, CATEGORY_LEVEL, URL_ROUTE_NAME)
VALUES  ('Foods & Drinks', 'Ready-to-eat foods and drinks', NULL, 0, 'foods-and-drinks'),
        ('Deli', 'Food made by the cook at time of order', NULL, 0, 'deli'),
        ('Frozen Goods', 'Frozen food and utilities', NULL, 0, 'frozen-goods'),
        ('Canned/Jarred Goods', 'Items preserved in a can or jar', NULL, 0, 'canned-and-jarred-goods'),
        ('Produce', 'Fruits, vegetables, and goods produced in a farm', NULL, 0, 'produce'),
        ('Grain Products', 'Products made of of grains such as bread and rice', NULL, 0, 'grain-products'),
        ('Electronics', 'Electronic items such as phone charges, batteries, among other things', NULL, 0, 'electronics'),
        ('Utilities', 'Everyday-life home utilities and personal care items', NULL, 0, 'utilities'),
        ('Clothing', 'Different sizes clothing items', NULL, 0, 'clothing'),
        ('More', 'Other less relevant categories for most customers', NULL, 0, 'more'),

-- Level 1 Categories------------------------------------------------------------------------

        ('Snacks', 'Snacks such as cookies, chips and sweets', 1, 1, 'snacks'),                   -- 11
        ('Beverages', 'Beverages such as soda, juices and hot beverages', 1, 1, 'beverages'),

        ('Hoagies', 'Different kinds of hoagies', 2, 1, 'hoagies'),
        ('Sandwiches', 'Different kinds of sandwiches', 2, 1, 'sandwiches'),
        ('Lunch Meat', 'Different kinds of lunch meat', 2, 1, 'lunch-meat'),
        ('Cheese', 'Different kinds of cheese', 2, 1, 'cheese'),

        ('Frozen Food', 'Food that is conserved frozen', 3, 1, 'frozen-food'),
        ('Frozen Utilities', 'Utilities that must be conserved frozen such as ice cubes', 3, 1, 'frozen-utilities'),

        ('Fruits', 'Different kinds of fruits', 5, 1, 'fruits'),
        ('Vegetables', 'Different kinds of vegetables', 5, 1, 'vegetables'),
        ('Animal Products', 'Different products produced by animals', 5, 1, 'animal-products'),

        ('Rice', 'Different kinds of rice', 6, 1, 'rice'),
        ('Bread', 'Different kinds of bread', 6, 1, 'bread'),

        ('Home Utilities', 'Utilities to do house work such as cleaning', 8, 1, 'home-utilities'),
        ('Personal Care', 'Utilities to do house work such as cleaning', 8, 1, 'personal-care'),

        ('Baby Goods', 'Baby utilities and foods such as diapers and baby clothing', 10, 1, 'baby-goods'),
        ('Automobile Goods', 'Automobile utilities such as antifreeze coolant', 10, 1, 'automobile-goods'),
        ('Pet Goods', 'Pet food and utilities such as litter', 10, 1, 'pet-goods'),
        ('Tobacco', 'Tobacco products such as cigarettes and cigars among others', 10, 1, 'tobacco'),

--------------------------------------------------------------------------------------------

        ('Sweets & Candies', 'Tobacco products such as cigarettes and cigars among others', 11, 2, 'sweets-and-candies'),     -- 30
        ('Chips', 'Different kinds of bags of Chips', 11, 2, 'chips'),
        ('Cookies', 'Different kinds of cookies', 11, 2, 'cookies'),
        ('Soda', 'Different kinds of Soda', 12, 2, 'soda'),
        ('Juices', 'Different kinds of juices', 12, 2, 'juices'),
        ('Hot Beverages', 'Different kinds of hot beverages', 12, 2, 'hot-beverages'),

        ('Green Veggies', 'Different kinds of green veggies', 20, 2, 'green-veggies'),
        ('Legumes', 'Different kinds of legumes', 20, 2, 'legumes'),
        ('Milk', 'Different kinds of milks', 21, 2, 'milk'),
        ('Eggs', 'Different kinds of eggs', 21, 2, 'eggs'),

        ('Pet Food', 'Different kinds of pet food such as dry and wet food', 28, 2, 'pet-food'),
        ('Pet Utilities', 'Different kinds of pet utilities such as litter', 28, 2, 'pet-utilities');


-----------------------------------------------------------------------------------------------

INSERT INTO ITEM_INVENTORY (QUANTITY)
VALUES  (18),
        (24);


---------------------------------------------------------------------------------------------

--INSERT INTO DISCOUNT (NAME, )
--VALUES  ();

---------------------------------------------------------------------------------------------

INSERT INTO DISCOUNT_TYPE (NAME, DESCRIPTION, PUBLIC_DESCRIPTION_TEMPLATE)
VALUES  ('BUY_AND_GET_FREE', 'Buy a specified amount of an specific item and get a given amount for free.', 'Buy <?1> <?2> and get <?3> <?2> for free'),
        ('CONTRACTUAL', 'An specified amount or percentage of the sale price is taken off the sale price at the point of sale.', 'Get <?1> off at checkout'),
        ('FREE_SHIPPING', 'Get free shipping on specified items.', 'Get free shipping when you buy <?1> <?2>');

--------------------------------------------------------------------------------------------------------------

INSERT INTO ITEM (NAME, DESCRIPTION, SKU, CATEGORY_ID, INVENTORY_ID, PRICE, IS_ON_SALE, IS_POPULAR)
VALUES  ('Coca-Cola bottle (16 oz)', '16 ounces Coca Cola bottle', '074312028328', 33, 1, 2.50, TRUE, TRUE),
        ('Red apple (Medium)', 'Medium-sized red apple', '028312928428', 19, 2, 0.50, TRUE, TRUE);

--------------------------------------------------------------------------------------------------------------

INSERT INTO FILE_TYPE (NAME, DESCRIPTION)
VALUES ('image', 'Images of different characteristics such as extension and size');

--------------------------------------------------------------------------------------------------

INSERT INTO FILE_RELATIVE_PATH (FILE_TYPE_ID, RELATIVE_PATH)
VALUES (1, 'api/client-specific/localhost/images/items/');

-------------------------------------------------------------------------------------------------

INSERT INTO ITEM_IMAGE (ITEM_ID, FILE_EXTENSION, SORT_NUMBER, FILE_RELATIVE_PATH_ID)
VALUES  (1, 'jpg', 1, 1),
        (2, 'jpg', 1, 1);

----------------------------------------------------------------------------------------

INSERT INTO CUSTOMER (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IS_DISABLED, TOTAL_CART_ITEMS)
VALUES ('anonymoususer@cornerfoodmarket.com', '', 'Anonymous', 'User', TRUE, 0);

---------------------------------------------------------------------------------------

INSERT INTO ADMINISTRATOR (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IS_DISABLED, IS_TFA_ENABLED, TFA_CHOSEN_TYPE)
VALUES ('amiguelp007@gmail.com', '$2a$10$N.kPTlzpYt6EsPPrpLavo.zxXOdg2Cr.vDet4kff5g53Q/.G6hry6', 'Angel', 'Peralta', FALSE, TRUE, 'EMAIL');

---------------------------------------------------------------------------------------



