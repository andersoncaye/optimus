CREATE TABLE pessoa(
	idpessoa int not null,
	tipo char(1) not null,
	ativo boolean not null,
	deleted boolean,
	deleted_time date,
	delete_userint int,

	CONSTRAINT pk_idpessoa PRIMARY KEY (idpessoa)

);

CREATE TABLE pessoafisica(
	idpessoa int not null,
	nome varchar(45) not null,
	apelido varchar(45),
	cpf varchar(12),
	rg varchar(12),

	CONSTRAINT pk_idpessoa_pessoafisica PRIMARY KEY (idpessoa),
	CONSTRAINT fk_idpessoa_pessoafisica FOREIGN KEY (idpessoa) REFERENCES pessoa
);

CREATE TABLE usuario(
	idusuario int not null,
	idpessoa int not null,
	nick varchar(45) not null, -- Deve-se alterar no esquema do banco NOT NULL e UNIQUE
	pass varchar(45) not null, -- Deve-se alterar no esquema do banco NOT NULL

	CONSTRAINT pk_idusuario PRIMARY KEY (idusuario),
	CONSTRAINT fk_idpessoa_usuario FOREIGN KEY (idpessoa) REFERENCES pessoa,
	CONSTRAINT un_nick_usuario UNIQUE (nick)
)