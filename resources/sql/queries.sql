-- name: create-user!
-- creates a new user record
INSERT INTO user
(first_name, last_name, email, password)
VALUES (:first_name, :last_name, :email, :password)


-- name: get-all-users
-- get all users
select id, user_name, first_name, last_name, email, password from user


-- name: get-user-by-login
-- get user by user's login
select id, user_name, first_name, last_name, email, password from user where email = :email

-- name: add-business!
-- add a business for a user
INSERT INTO business
    (user_id, business_name)
    VALUES
    (:user_id, :business_name)

-- name: get-business-by-user
-- get the business for a user
SELECT * FROM business WHERE user_id = :user_id

-- name: add-item!
-- add an item for a user
INSERT INTO item (business_id, code, description, price, stock, create_date, last_modify_date) VALUES(:business_id, :code, :description, :price, :stock, :create_date, :last_modify_date)

-- name: get-items-by-business
-- get the list of items of a business
SELECT * FROM item WHERE business_id = :business_id
