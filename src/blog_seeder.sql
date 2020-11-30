USE blog_db;

Drop TABLE posts;
DROP TABLE users;

INSERT INTO users (email, password, username) VALUES ('jimeagle@hello.org', 'rockingeagle', 'jeagle'),
                                                     ('melanie@example.org', 'melanierocks', 'themelanie');

INSERT INTO posts (body, title, user_id) VALUES (
                                           'We all need to eat popcorn. The main reason why is because popcorn is a quick to feel full even though we don\'t eat a whole lot of food overall. Popcorn should rain from the skies.',
                                              'My favorite snack',
                                                 1
                                       ),
                                       (
                                        'Well, we really don''t know what to do at times. Maybe we should just code and think it is okay. Or maybe...just maybe...we can just see if this works.',
                                        'What to do',
                                        1
                                       );

