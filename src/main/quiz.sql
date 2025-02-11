CREATE DATABASE IF NOT EXISTS quiz_app_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE quiz_app_db;

CREATE TABLE IF NOT EXISTS Contact (
                                       contact_id INT AUTO_INCREMENT,
                                       subject VARCHAR(255) NOT NULL,
                                       message TEXT NOT NULL,
                                       email VARCHAR(255) NOT NULL,
                                       `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (contact_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `User` (
                                      user_id INT AUTO_INCREMENT,
                                      email VARCHAR(255) NOT NULL,
                                      password VARCHAR(255) NOT NULL,
                                      firstname VARCHAR(50),
                                      lastname VARCHAR(50),
                                      is_active TINYINT(1) NOT NULL DEFAULT 1,
                                      is_admin TINYINT(1) NOT NULL DEFAULT 0,
                                      PRIMARY KEY (user_id),
                                      UNIQUE KEY uk_user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Category (
                                        category_id INT AUTO_INCREMENT,
                                        name VARCHAR(255) NOT NULL,
                                        PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Question (
                                        question_id INT AUTO_INCREMENT,
                                        category_id INT NOT NULL,
                                        description TEXT NOT NULL,
                                        is_active TINYINT(1) NOT NULL DEFAULT 1,
                                        PRIMARY KEY (question_id),
                                        CONSTRAINT fk_question_category
                                            FOREIGN KEY (category_id)
                                                REFERENCES Category(category_id)
                                                ON DELETE RESTRICT
                                                ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Choice (
                                      choice_id INT AUTO_INCREMENT,
                                      question_id INT NOT NULL,
                                      description TEXT NOT NULL,
                                      is_correct TINYINT(1) NOT NULL DEFAULT 0,
                                      PRIMARY KEY (choice_id),
                                      CONSTRAINT fk_choice_question
                                          FOREIGN KEY (question_id)
                                              REFERENCES Question(question_id)
                                              ON DELETE CASCADE
                                              ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Quiz (
                                    quiz_id INT AUTO_INCREMENT,
                                    user_id INT NOT NULL,
                                    category_id INT NOT NULL,
                                    name VARCHAR(255) NOT NULL,
                                    time_start TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    time_end TIMESTAMP NULL,
                                    PRIMARY KEY (quiz_id),
                                    CONSTRAINT fk_quiz_user
                                        FOREIGN KEY (user_id)
                                            REFERENCES `User`(user_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE,
                                    CONSTRAINT fk_quiz_category
                                        FOREIGN KEY (category_id)
                                            REFERENCES Category(category_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS QuizQuestion (
                                            qq_id INT AUTO_INCREMENT,
                                            quiz_id INT NOT NULL,
                                            question_id INT NOT NULL,
                                            user_choice_id INT NULL,
                                            PRIMARY KEY (qq_id),
                                            CONSTRAINT fk_qq_quiz
                                                FOREIGN KEY (quiz_id)
                                                    REFERENCES Quiz(quiz_id)
                                                    ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
                                            CONSTRAINT fk_qq_question
                                                FOREIGN KEY (question_id)
                                                    REFERENCES Question(question_id)
                                                    ON DELETE CASCADE
                                                    ON UPDATE CASCADE,
                                            CONSTRAINT fk_qq_choice
                                                FOREIGN KEY (user_choice_id)
                                                    REFERENCES Choice(choice_id)
                                                    ON DELETE SET NULL
                                                    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



INSERT INTO Category (name) VALUES
                                ('Java Programming'),
                                ('Web Development Basics'),
                                ('Computer Science Fundamentals');


INSERT INTO Question (category_id, description) VALUES
                                                    (1, 'Which keyword is used to define a constant in Java?'),
                                                    (1, 'What is the default value of a boolean variable in Java?'),
                                                    (1, 'Which collection implements a dynamic array in Java?'),
                                                    (1, 'Which method must be implemented by all threads in Java?'),
                                                    (1, 'What is the parent class of all Java classes?'),
                                                    (1, 'Which keyword is used for method overriding?'),
                                                    (1, 'What does JVM stand for?'),
                                                    (1, 'Which interface provides the compareTo() method?'),
                                                    (1, 'What is the size of a long variable in Java?'),
                                                    (1, 'Which exception is thrown when dividing by zero?');

-- Web Development Basics (category_id=2)
INSERT INTO Question (category_id, description) VALUES
                                                    (2, 'What does HTML stand for?'),
                                                    (2, 'Which CSS property controls text size?'),
                                                    (2, 'What is the correct HTML element for largest heading?'),
                                                    (2, 'Which HTTP status code means "Not Found"?'),
                                                    (2, 'What does CSS stand for?'),
                                                    (2, 'Which HTML attribute specifies an alternate text for an image?'),
                                                    (2, 'Which HTML element contains the meta information?'),
                                                    (2, 'What is the default HTTP request method?'),
                                                    (2, 'Which character is used to indicate an end tag?'),
                                                    (2, 'What does URL stand for?');

-- Computer Science Fundamentals (category_id=3)
INSERT INTO Question (category_id, description) VALUES
                                                    (3, 'What is the time complexity of bubble sort in worst case?'),
                                                    (3, 'Which data structure uses FIFO principle?'),
                                                    (3, 'What does CPU stand for?'),
                                                    (3, 'How many bits are in a byte?'),
                                                    (3, 'Which search algorithm requires sorted data?'),
                                                    (3, 'What is the main advantage of a linked list over array?'),
                                                    (3, 'Which protocol is used for secure web communication?'),
                                                    (3, 'What is the result of 1 MB in bytes?'),
                                                    (3, 'Which sorting algorithm has the best average time complexity?'),
                                                    (3, 'What does LAN stand for?');



-- Insert Choices (4 per question, 120 total)
-- Java Programming (questions 1-10)
-- Question 1
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (1, 'const', 0),
                                                              (1, 'final', 1),
                                                              (1, 'static', 0),
                                                              (1, 'constant', 0);

-- Question 2
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (2, 'true', 0),
                                                              (2, 'false', 1),
                                                              (2, 'null', 0),
                                                              (2, '0', 0);

-- Question 3
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (3, 'HashSet', 0),
                                                              (3, 'LinkedList', 0),
                                                              (3, 'ArrayList', 1),
                                                              (3, 'TreeMap', 0);

-- Question 4
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (4, 'start()', 0),
                                                              (4, 'run()', 1),
                                                              (4, 'execute()', 0),
                                                              (4, 'begin()', 0);

-- Question 5
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (5, 'Object', 1),
                                                              (5, 'Class', 0),
                                                              (5, 'Super', 0),
                                                              (5, 'Main', 0);

-- Question 6
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (6, '@Override', 1),
                                                              (6, '@Overwrite', 0),
                                                              (6, '@Implement', 0),
                                                              (6, '@Extend', 0);

-- Question 7
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (7, 'Java Virtual Machine', 1),
                                                              (7, 'Java Visual Manager', 0),
                                                              (7, 'Javascript Variable Module', 0),
                                                              (7, 'Java Version Manager', 0);

-- Question 8
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (8, 'Comparable', 1),
                                                              (8, 'Comparator', 0),
                                                              (8, 'Serializable', 0),
                                                              (8, 'Cloneable', 0);

-- Question 9
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (9, '32 bits', 0),
                                                              (9, '64 bits', 1),
                                                              (9, '16 bits', 0),
                                                              (9, 'Depends on JVM', 0);

-- Question 10
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (10, 'NullPointerException', 0),
                                                              (10, 'ArithmeticException', 1),
                                                              (10, 'NumberFormatException', 0),
                                                              (10, 'IllegalArgumentException', 0);

-- Web Development (questions 11-20)
-- Question 11
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (11, 'Hyper Text Markup Language', 1),
                                                              (11, 'Home Tool Markup Language', 0),
                                                              (11, 'Hyperlinks and Text Markup Language', 0),
                                                              (11, 'High-level Text Management Language', 0);

-- Question 12
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (12, 'text-size', 0),
                                                              (12, 'font-size', 1),
                                                              (12, 'text-style', 0),
                                                              (12, 'font-family', 0);

-- Question 13
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (13, '<h6>', 0),
                                                              (13, '<heading>', 0),
                                                              (13, '<h1>', 1),
                                                              (13, '<head>', 0);

-- Question 14
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (14, '200', 0),
                                                              (14, '404', 1),
                                                              (14, '500', 0),
                                                              (14, '302', 0);

-- Question 15
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (15, 'Cascading Style Sheets', 1),
                                                              (15, 'Computer Style Sheets', 0),
                                                              (15, 'Creative Style System', 0),
                                                              (15, 'Colorful Style Syntax', 0);

-- Question 16
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (16, 'src', 0),
                                                              (16, 'href', 0),
                                                              (16, 'alt', 1),
                                                              (16, 'title', 0);

-- Question 17
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (17, '<body>', 0),
                                                              (17, '<header>', 0),
                                                              (17, '<head>', 1),
                                                              (17, '<meta>', 0);

-- Question 18
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (18, 'POST', 0),
                                                              (18, 'PUT', 0),
                                                              (18, 'GET', 1),
                                                              (18, 'HEAD', 0);

-- Question 19
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (19, '*', 0),
                                                              (19, '#', 0),
                                                              (19, '/', 1),
                                                              (19, '\\', 0);

-- Question 20
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (20, 'Uniform Resource Locator', 1),
                                                              (20, 'Universal Reference Link', 0),
                                                              (20, 'United Resource Library', 0),
                                                              (20, 'Uniform Rendering Language', 0);

-- Computer Science (questions 21-30)
-- Question 21
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (21, 'O(n)', 0),
                                                              (21, 'O(n log n)', 0),
                                                              (21, 'O(n²)', 1),
                                                              (21, 'O(1)', 0);

-- Question 22
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (22, 'Stack', 0),
                                                              (22, 'Queue', 1),
                                                              (22, 'Tree', 0),
                                                              (22, 'Graph', 0);

-- Question 23
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (23, 'Central Processing Unit', 1),
                                                              (23, 'Computer Processing Unit', 0),
                                                              (23, 'Core Processing Unit', 0),
                                                              (23, 'Central Protocol Unit', 0);

-- Question 24
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (24, '4', 0),
                                                              (24, '8', 1),
                                                              (24, '16', 0),
                                                              (24, '32', 0);

-- Question 25
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (25, 'Linear Search', 0),
                                                              (25, 'Binary Search', 1),
                                                              (25, 'Hash Search', 0),
                                                              (25, 'Depth-First Search', 0);

-- Question 26
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (26, 'Faster access time', 0),
                                                              (26, 'Dynamic size adjustment', 1),
                                                              (26, 'Better cache locality', 0),
                                                              (26, 'Lower memory usage', 0);

-- Question 27
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (27, 'HTTP', 0),
                                                              (27, 'FTP', 0),
                                                              (27, 'HTTPS', 1),
                                                              (27, 'SMTP', 0);

-- Question 28
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (28, '1024 bytes', 0),
                                                              (28, '1000 bytes', 0),
                                                              (28, '1048576 bytes', 1),
                                                              (28, '1000000 bytes', 0);

-- Question 29
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (29, 'Bubble Sort', 0),
                                                              (29, 'Quick Sort', 1),
                                                              (29, 'Insertion Sort', 0),
                                                              (29, 'Selection Sort', 0);

-- Question 30
INSERT INTO Choice (question_id, description, is_correct) VALUES
                                                              (30, 'Local Area Network', 1),
                                                              (30, 'Large Access Node', 0),
                                                              (30, 'Logical Address Number', 0),
                                                              (30, 'Linked Application Network', 0);













