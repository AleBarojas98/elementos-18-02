

create table usuario( cuenta integer primary key , nombre varchar(40), email varchar(40));
create table direccion( id integer primary key auto_increment, cuenta integer , calle varchar(100), cp integer, municipio varchar(40),foreign key(cuenta)
        references usuario(cuenta));

select * from usuario;
select * from direccion;

delete direccion;
delete usuario;