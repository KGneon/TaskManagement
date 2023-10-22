DROP DATABASE IF EXISTS task_database;
CREATE DATABASE IF NOT EXISTS task_database;
USE task_database;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    user_id INT
);

CREATE TABLE tasks_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT,
    user_id INT,
    FOREIGN KEY (task_id) REFERENCES tasks(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (name, surname, email) VALUES
    ('John', 'Doe', 'john.doe@example.com'),
    ('Jane', 'Smith', 'jane.smith@example.com'),
    ('Michael', 'Johnson', 'michael.johnson@example.com'),
    ('Emily', 'Brown', 'emily.brown@example.com'),
    ('William', 'Davis', 'william.davis@example.com'),
    ('Sophia', 'Wilson', 'sophia.wilson@example.com'),
    ('Matthew', 'Taylor', 'matthew.taylor@example.com'),
    ('Olivia', 'Harris', 'olivia.harris@example.com'),
    ('David', 'Clark', 'david.clark@example.com'),
    ('Ella', 'Allen', 'ella.allen@example.com');

-- Insert sample data into the 'tasks' table
INSERT INTO tasks (title, description, user_id) VALUES
    ('Task 1', 'Description for Task 1', 1),
    ('Task 2', 'Description for Task 2', 1),
    ('Task 3', 'Description for Task 3', 2),
    ('Task 4', 'Description for Task 4', 2),
    ('Task 5', 'Description for Task 5', 3),
    ('Task 6', 'Description for Task 6', 4),
    ('Task 7', 'Description for Task 7', 5),
    ('Task 8', 'Description for Task 8', 6),
    ('Task 9', 'Description for Task 9', 7),
    ('Task 10', 'Description for Task 10', 8);
