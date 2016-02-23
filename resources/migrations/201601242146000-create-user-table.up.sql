CREATE TABLE IF NOT EXISTS User(id IDENTITY, email VARCHAR(50) UNIQUE, first_name VARCHAR(50), last_name VARCHAR(50), password VARCHAR(200));

CREATE TABLE IF NOT EXISTS Business(id IDENTITY, user_id INT, business_name VARCHAR(50));

CREATE TABLE IF NOT EXISTS Item(id IDENTITY, business_id INT NOT NULL, code VARCHAR(50), description VARCHAR(250), price DECIMAL(10,2), thumbnail BINARY, create_date TIMESTAMP, last_modify_date TIMESTAMP,FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS Image(id IDENTITY, item_id INT NOT NULL, image BINARY, FOREIGN KEY (ITEM_ID) references Item(id));

CREATE TABLE IF NOT EXISTS Client(id IDENTITY, wei_id varchar(250), business_id INT, name varchar(50), create_date TIMESTAMP, last_modify_date TIMESTAMP, CONSTRAINT uc_id_user_id UNIQUE (id, business_id), FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS ClientOrder(id IDENTITY, client_id INT, business_id INT, create_date TIMESTAMP, last_modify_date TIMESTAMP, FOREIGN KEY (client_id) REFERENCES Client(id), FOREIGN KEY (business_id) REFERENCES Business(id));

CREATE TABLE IF NOT EXISTS OrderItem (id IDENTITY, order_id INT, item_id INT, quantity INT, price DECIMAL(10,2), create_date TIMESTAMP, last_modify_date TIMESTAMP, FOREIGN KEY (item_id) REFERENCES Item(id), FOREIGN KEY (order_id) REFERENCES ClientOrder(id));
