-- name: create-user!
-- creates a new user record
INSERT INTO user
(first_name, last_name, email, password)
VALUES (:first_name, :last_name, :email, :password)


-- name: get-all-users
-- get all users
select id, first_name, last_name, email, password from user


-- name: get-user-by-login
-- get user by user's login
select id, first_name, last_name, email, password from user where email = :email
