create table if not exists category
(
    id int not null primary key ,
    description varchar,
    name varchar
);
create table if not exists product
(
    id int not null primary key ,
    description varchar,
    name varchar,
    available_quantity double precision not null,
    price numeric(38,2),
    category_id integer
            constraint fk1ooooo references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
