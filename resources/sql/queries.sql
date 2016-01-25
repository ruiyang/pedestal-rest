-- name: create-user!
-- creates a new user record
INSERT INTO user
(first_name, last_name, email, pass)
VALUES (:first_name, :last_name, :email, :pass)


-- name: get-all-users
-- get all users
select id, first_name, last_name, email, pass from user


-- name: get-user-by-login
-- get user by user's login
select id, first_name, last_name, email, pass from user where email = :email
