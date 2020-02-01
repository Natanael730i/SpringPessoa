create table Endereco(
    idEndereco int primary key ,
    logradouro varchar(40),
    bairro varchar(40),
    numero varchar(40)
);
create table Pessoa(
    id int primary key,
    nome varchar(40),
    anoNascimento date,
    cpf varchar(40),
    idEndereco int,
    foreign key (idEndereco) references Endereco(idEndereco)
);
