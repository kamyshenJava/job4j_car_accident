create table if not exists types(
    id serial primary key,
    name text
);

create table if not exists rules(
    id serial primary key,
    name text
);

create table if not exists accidents(
    id serial primary key,
    name text,
    text text,
    address text,
    type_id int references types(id)
);

create table if not exists accidents_rules(
    accident_id int references accidents(id),
    rule_id int references rules(id)
)