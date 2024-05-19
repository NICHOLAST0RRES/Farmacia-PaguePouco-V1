
create table medicamentos(

                             id bigint not null auto_increment,
                             nome varchar(100) not null,
                             preco varchar(100) not null,
                             descricao varchar(100) not null,
                             validade varchar(100) not null,
                             quantidade varchar(100) not null,
                             ativo tinyint not null,

                             primary key(id)

);

