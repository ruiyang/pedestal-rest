CREATE TABLE IF NOT EXISTS User(id IDENTITY, user_name VARCHAR(50), user_type INT, email VARCHAR(50) UNIQUE, first_name VARCHAR(50), last_name VARCHAR(50), password VARCHAR(200));

CREATE TABLE IF NOT EXISTS Business(id IDENTITY, user_id INT, business_name VARCHAR(50));

CREATE TABLE IF NOT EXISTS UserBusiness(id IDENTITY, user_id INT, business_id INT, FOREIGN KEY (user_id) REFERENCES User(id), FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS Item(id IDENTITY, business_id INT NOT NULL, item_name VARCHAR(150), code VARCHAR(50), description VARCHAR(1000), price DECIMAL(10,2), stock INT NULL DEFAULT(0), thumbnail BINARY, create_date TIMESTAMP, last_modify_date TIMESTAMP,FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS Image(id IDENTITY, item_id INT NOT NULL, image BINARY, FOREIGN KEY (ITEM_ID) references Item(id));

CREATE TABLE IF NOT EXISTS Client(id IDENTITY, business_id INT, user_id INT, client_type INT, create_date TIMESTAMP, last_modify_date TIMESTAMP, CONSTRAINT uc_id_user_id UNIQUE (business_id, user_id), FOREIGN KEY (business_id) REFERENCES Business(id), FOREIGN KEY (user_id) REFERENCES User(id));

CREATE TABLE IF NOT EXISTS ClientOrder(id IDENTITY, client_id INT, business_id INT, create_date TIMESTAMP, last_modify_date TIMESTAMP, FOREIGN KEY (client_id) REFERENCES Client(id), FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS OrderItem (id IDENTITY, order_id INT, item_id INT, quantity INT, price DECIMAL(10,2), create_date TIMESTAMP, last_modify_date TIMESTAMP, FOREIGN KEY (item_id) REFERENCES Item(id), FOREIGN KEY (order_id) REFERENCES ClientOrder(id));
