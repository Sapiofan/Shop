create table users
(
    id         bigserial primary key,
    name       text not null,
    surname    text not null,
    password   text not null,
    email      text unique not null,
    admin      boolean default false,
    created_at timestamp default now()
);

create table career
(
    id         bigserial primary key,
    name       text not null,
    surname    text not null,
    email      text unique not null,
    phone      text unique not null,
    position   text not null,
    link       text not null,
    created_at timestamp default now()
);

create table contacts
(
    id         bigserial primary key,
    name       text not null,
    surname    text not null,
    email      text unique not null,
    send_mails boolean default false
);

create table contact_messages
(
    id         bigserial primary key,
    subject    text not null,
    message    text not null,
    sent_at    timestamp default now(),
    contact_id bigint references contacts(id) on delete cascade
);

create table categories
(
    id         bigserial primary key,
    name       text unique not null
);

create table products
(
    id         bigserial primary key,
    name       text unique not null,
    price      float not null,
    brand      text not null,
    category_id bigint references categories(id)
);
