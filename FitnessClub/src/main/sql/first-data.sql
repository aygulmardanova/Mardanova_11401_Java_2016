INSERT INTO users(login, password, email, name, surname, photo, phone_number, role)
VALUES ('Aig', '123', 'aig@yandex.com', 'Aygul', 'Mardanova', 'penguin.jpg', '8917272727', 'ROLE_INSTRUCTOR');

INSERT INTO instructor(users_id, description, qualification, awards, experience)
VALUES (1, 'The best trainer you have ever seen', 'Studying in KFU, ITIS', 'No awards yet', to_date('13.05.2013', 'dd.mm.yyyy'));

INSERT INTO users(login, password, email, name, surname, photo, phone_number, role)
VALUES ('Elvira', '123', 'elvira@gmail.com', 'Elvira', 'Batyrova', 'penguin.jpg', '8917222222', 'ROLE_USER');

insert into class (name, description, photo) VALUES ('Yoga', 'Yoga – a holistic and multi-dimensional theory of knowledge of ourselves and the world around us that has a thousand year of history. Yoga – is very specific science as there are many specific exercises and techniques, allowing to realize their inner potential, reveal inherent in every person colossal physical and mental abilities, keep yourself in good physical shape. On yoga classes, people leave “monotony of everyday life” and go into new, absolutely fantastic and magical world becoming more open, sincere, kind, begin to live more vibrant, rich, full life. Yoga not only helps to improve physical health, find a strong, slender, hardy body, but also allows you to fully realize yourself in all areas of life – work and school, in your career and business, family and interpersonal relationships, professional and personal development, and many other areas of human activity. This gift of universe, which has been left to us by teachers of antiquity, which already achieved all the goals that we can only dream of.', 'yoga.jpg');

insert into class(name, description, photo) VALUES ('Total Conditioning', 'This class offers a total body workout consisting of cardiovascular intervals, strength training, core work and flexibility. Every muscle is worked and you may find muscles you did not know you had! Be prepared to move, sweat, balance, learn and laugh.', null);

insert into class(name, description, photo) values ('Zumba', 'Zumba is a fun, easy-to-follow aerobic class that combines Latin dance moves with cardio training. Its a wildly popular way to maximize caloric output while toning your whole body. Zumba takes the work out of working out.', 'zumba.jpg');

insert into class(name, description, photo) VALUES ('Pilates', 'Pilates is group of exercises developed by Joseph Pilates that are designed to strengthen and tone your muscles through core body training.', 'pilates.jpg');

insert into class(name, description, photo) VALUES ('BodyPump', 'BODYPUMP is the original barbell class that strengthens your entire body. This workout challenges all your major muscle groups by using the best weight-room exercises such as squats, presses, lifts and curls. Great music, awesome instructors and your choice of weight inspire you to get the results you came for!', 'bodypump.jpg');