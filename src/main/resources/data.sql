insert into categories (id, name) values
    (gen_random_uuid(), 'Edukacja'),
    (gen_random_uuid(), 'Sport'),
    (gen_random_uuid(), 'Zdrowie'),
    (gen_random_uuid(), 'Zakupy'),
    (gen_random_uuid(), 'Rozrywka'),
    (gen_random_uuid(), 'Samorozwój');

insert into tasks (id, name, priority, category_id) values
    (gen_random_uuid(), 'Bieganie', 6, (select id from categories WHERE name = 'Sport')),
    (gen_random_uuid(), '50 pompek', 5, (select id from categories WHERE name = 'Sport')),
    (gen_random_uuid(), 'Jazda na rowerze', 2, (select id from categories WHERE name = 'Sport')),
    (gen_random_uuid(), 'Programowanie', 9, (select id from categories WHERE name = 'Edukacja')),
    (gen_random_uuid(), 'Czytanie ksiazki', 4, (select id from categories WHERE name = 'Edukacja'));