INSERT into users(login, password, email, name, surname, photo, phone_number, role)
VALUES ('Aig', '123', 'aig@yandex.com', 'Aygul', 'Mardanova', 'penguin.jpg', '8917272727', 'ROLE_INSTRUCTOR');

INSERT into instructor(users_id, description, qualification, awards, experience)
VALUES (1, 'The best trainer you have ever seen', 'Studying in KFU, ITIS', 'No awards yet', to_date('13.05.2013', 'dd.mm.yyyy'));

INSERT into users(login, password, email, name, surname, photo, phone_number, role)
VALUES ('Elvira', '123', 'elvira@gmail.com', 'Elvira', 'Batyrova', 'penguin.jpg', '8917222222', 'ROLE_USER');

