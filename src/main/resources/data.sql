insert into categories (id, name) values
    (gen_random_uuid(), 'Edukacja'),
    (gen_random_uuid(), 'Sport'),
    (gen_random_uuid(), 'Zdrowie'),
    (gen_random_uuid(), 'Zakupy'),
    (gen_random_uuid(), 'Rozrywka'),
    (gen_random_uuid(), 'Samorozwój');

insert into tasks (id, name, category_id) values
    (gen_random_uuid(), 'Bieganie', (select id from categories WHERE name = 'Sport')),
    (gen_random_uuid(), '50 pompek', (select id from categories WHERE name = 'Sport')),
    (gen_random_uuid(), 'Jazda na rowerze', (select id from categories WHERE name = 'Sport'));