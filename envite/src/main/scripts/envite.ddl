
    create table authorizes (
        authorize_id int4 not null,
        authority varchar(255),
        username varchar(255),
        users_id int4,
        primary key (authorize_id)
    );

    create table events (
        eid int4 not null,
        event_message varchar(255),
        event_name varchar(255),
        Picture bytea,
        users_id int4,
        primary key (eid)
    );

    create table invite (
        invite_id int4 not null,
        accepted varchar(255) default null,
        guestEmail varchar(255),
        guestName varchar(255),
        messageToHost varchar(255),
        message_id varchar(255),
        status boolean not null,
        primary key (invite_id)
    );

    create table invite_events (
        invite_invite_id int4 not null,
        events_eid int4 not null
    );

    create table users (
        id int4 not null,
        enabled boolean not null,
        f_name varchar(255),
        l_name varchar(255),
        password varchar(255),
        user_email varchar(255),
        username varchar(255),
        primary key (id)
    );

    alter table authorizes 
        add constraint FK_rid3krh08hbt7710l4cms27ke 
        foreign key (users_id) 
        references users;

    alter table events 
        add constraint FK_kvyrbe1ihs35qltiiidrycpi3 
        foreign key (users_id) 
        references users;

    alter table invite_events 
        add constraint FK_3t4aoy0o0ua5275p7fw5pp5vs 
        foreign key (events_eid) 
        references events;

    alter table invite_events 
        add constraint FK_c7t31hyl5sigohgfmobj2ju84 
        foreign key (invite_invite_id) 
        references invite;

    create sequence hibernate_sequence minvalue 2;
    insert into users values(1,true,'a','a','a','a','a');
