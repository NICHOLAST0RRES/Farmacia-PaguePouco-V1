create table famaceutico(

                        id bigint not null auto_increment,
                        nome varchar(100) not null,
                        tipo varchar(100) not null,
                        crf varchar(6) not null unique,
                        turno varchar(100) not null,
                        especialidade varchar(100) not null,

                        primary key(id)

);