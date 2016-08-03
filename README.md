CREATE TABLE user(
    id int primary key auto_increment not null,
    account varchar(255) not null,
    password varchar(255) not null,
    reg_date timestamp not null default NOW(),

    UNIQUE KEY user_account_key (account)
);



CREATE TABLE post(
    id int primary key auto_increment not null,
    user_id int not null,
    title varchar(255) not null,
    content text not null,
    reg_date timestamp not null default NOW(),

    FOREIGN KEY (user_id) REFERENCES user(id)
)