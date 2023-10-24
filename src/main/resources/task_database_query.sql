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
    status VARCHAR(20),
    end_date DATE NOT NULL,
    user_id INT,
    expected_users INT NOT NULL
);

CREATE TABLE tasks_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT,
    user_id INT,
    FOREIGN KEY (task_id) REFERENCES tasks(id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
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
    ('Ella', 'Allen', 'ella.allen@example.com'),
    ('Sonia', 'Will', 'sonia.will@example.com'),
    ('Mark', 'Marks', 'mark.marks@example.com'),
    ('Alex', 'Hey', 'alex.hey@example.com'),
    ('Brad', 'Coop', 'brad.coop@example.com'),
    ('Emilia', 'Clair', 'emilia.clair@example.com'),
    ('John', 'Dian', 'john.dian@example.com'),
    ('John', 'Doe', 'john.doe.jd@example.com'),
    ('Wallace', 'Marks', 'wallace.marks@example.com'),
    ('Sienna', 'Gill', 'siennagill.sg@example.com'),
    ('Arianna', 'Ann', 'airanna.ann@example.com');


-- Insert sample data into the 'tasks' table
INSERT INTO tasks (title, description, status, end_date, expected_users) VALUES
    ('Task 1', 'Description for Task 1', NULL, '2024-10-24', 2),
    ('Task 2', 'Description for Task 2', 'UNASSIGNED', '2024-08-04', 3),
    ('Task 3', 'Description for Task 3', 'ASSIGNED', '2023-10-10', 1),
    ('Task 4', 'Description for Task 4', 'IN_PROGRESS', '2023-10-24', 2),
    ('Task 5', 'Description for Task 5', NULL, '2023-12-20', 1),
    ('Task 6', 'Description for Task 6', 'IN_PROGRESS', '2023-11-24', 3),
    ('Task 7', 'Description for Task 7', 'CANCELLED', '2024-01-24', 2),
    ('Task 8', 'Description for Task 8', 'IN_PROGRESS', '2024-05-01', 1),
    ('Task 9', 'Description for Task 9', 'LATE_IN_PROGRESS', '2023-10-15', 1),
    ('Task 10', 'Description for Task 10', 'ASSIGNED', '2023-11-02', 4);

INSERT INTO tasks_users (task_id, user_id) VALUES
    (2, 3),
    (2, 4),
    (3, 1),
    (4, 5),
    (4, 7),
    (6, 10),
    (6, 11),
    (6, 12),
    (7, 9),
    (7, 8),
    (8, 13),
    (9, 14),
    (10, 15),
    (10, 16),
    (10, 17),
    (10, 18);


