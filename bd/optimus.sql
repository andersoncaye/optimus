-- ############### Criar tabela


-- Database: optimus_controle_producao

-- DROP DATABASE optimus_controle_producao;

CREATE DATABASE optimus_controle_producao
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- ############### Criar tabela Usuario

CREATE TABLE IF NOT EXISTS usuario (
	idUsuario SERIAL NOT NULL,
	nome VARCHAR(100),
	nick VARCHAR(45) NOT NULL,
	pass VARCHAR(45) NOT NULL,
	ativo boolean NOT NULL default true,
	deleted boolean NOT NULL default false,
	
	CONSTRAINT pk_idusuario PRIMARY KEY (idUsuario),
	CONSTRAINT un_nick UNIQUE (nick)
	
);

INSERT INTO usuario (nome, nick, pass) VALUES ('Anderson Caye', 'anderson.caye', 'an');
INSERT INTO usuario (nome, nick, pass) VALUES ('Alan Caye', 'alan.caye', 'al');
INSERT INTO usuario (nome, nick, pass) VALUES ('Elias Santos', 'elias.santos', 'es');
INSERT INTO usuario (nome, nick, pass) VALUES ('Admin', 'admin', 'admin');

-- ############### Criar tabela Fornecedor

CREATE TABLE IF NOT EXISTS fornecedor (
	idFornecedor SERIAL NOT NULL,
	razaoSocial VARCHAR(45) NOT NULL,
	nomeFantasia VARCHAR(45),
	cnpj VARCHAR(45),
	ie VARCHAR(45),
	endereco VARCHAR(45),
	cidade VARCHAR(45),
	estado VARCHAR(45),
	ativo boolean NOT NULL default true,
	deleted boolean NOT NULL default false,

	CONSTRAINT pk_idFornecedor PRIMARY KEY (idFornecedor)
);

INSERT INTO fornecedor (razaoSocial, ativo, deleted) VALUES ('Fornecedor Brocas Diversas', true, false);
INSERT INTO fornecedor (razaoSocial, ativo, deleted) VALUES ('Fornecedor Serrras Diversas', true, false);
INSERT INTO fornecedor (razaoSocial, ativo, deleted) VALUES ('Fornecedor Rebolos Diversos', true, false);

-- ############### Criar tabela Funcionário

CREATE TABLE IF NOT EXISTS funcionario(
		idFuncionario SERIAL NOT NULL,
		nome VARCHAR(45) NOT NULL,
		apelido VARCHAR(45),
		cpf VARCHAR(45),
		rg VARCHAR(45),
		endereco VARCHAR(45),
		cidade VARCHAR(45),
		estado VARCHAR(2),
		data_Ingresso DATE NOT NULL,
		data_Admissao DATE,
		data_Demissao DATE,
		salario DECIMAL,
		funcao VARCHAR(45),
		ativo boolean NOT NULL default true,
		deleted boolean NOT NULL default false,

		CONSTRAINT pk_idFuncionario PRIMARY KEY (idFuncionario) 
);

INSERT INTO funcionario (nome, data_Ingresso, ativo, deleted) VALUES ('Anderson', '07/04/1997', true, false);
INSERT INTO funcionario (nome, data_Ingresso, ativo, deleted) VALUES ('Fabrício', '07/04/1997', true, false);
INSERT INTO funcionario (nome, data_Ingresso, ativo, deleted) VALUES ('Lucas', '07/04/1997', true, false);
INSERT INTO funcionario (nome, data_Ingresso, ativo, deleted) VALUES ('Filipe', '07/04/1997', true, false);
INSERT INTO funcionario (nome, data_Ingresso, ativo, deleted) VALUES ('Geraldo', '07/04/1997', true, false);

-- ############### Criar tabela Ferramenta

CREATE TABLE IF NOT EXISTS ferramenta (
	idFerramenta SERIAL NOT NULL,
	codigo VARCHAR(45) NOT NULL,
	descricao VARCHAR(500) NOT NULL,
	idFornecedor INT NOT NULL,
	ativo boolean NOT NULL default true,
	deleted boolean NOT NULL default false,

	CONSTRAINT pk_idFerramenta PRIMARY KEY (idFerramenta),
	CONSTRAINT fk_idFornecedor_ferramenta FOREIGN KEY (idFornecedor) REFERENCES fornecedor,
	CONSTRAINT un_codigo_ferramenta UNIQUE (codigo)

);

INSERT INTO ferramenta (codigo, descricao, idFornecedor, ativo, deleted) 
				VALUES ('BROCA 001', 'BROCA DIAMANTADA EXT 8,5MM', 1, true, false);
INSERT INTO ferramenta (codigo, descricao, idFornecedor, ativo, deleted) 
				VALUES ('BROCA 002', 'BROCA DIAMANTADA EXT 10MM', 1, true, false);
INSERT INTO ferramenta (codigo, descricao, idFornecedor, ativo, deleted) 
				VALUES ('REBOLO 001', 'REBOLO DIAMANTADO FORMATO "V" C/ ÂNGULO 30º', 3, true, false);
INSERT INTO ferramenta (codigo, descricao, idFornecedor, ativo, deleted) 
				VALUES ('REBOLO 002', 'REBOLO DIAMANTADO FORMATO MEIO CIRVULO C/ RAIO 12MM', 3, true, false);
INSERT INTO ferramenta (codigo, descricao, idFornecedor, ativo, deleted) 
				VALUES ('SERRA 001', 'SERRA DIAMANTADA 600MM', 2, true, false);

-- ############### Criar tabela Produto_Material

CREATE TABLE IF NOT EXISTS produto_material (
	idProdutoMaterial SERIAL NOT NULL,
	codigo VARCHAR (45) NOT NULL,
	descricao VARCHAR (500) NOT NULL,
	unidade VARCHAR (10) NOT NULL,
	ativo boolean NOT NULL default true,
	deleted boolean NOT NULL default false,

	CONSTRAINT pk_idProdutoMaterial PRIMARY KEY (idProdutoMaterial),
	CONSTRAINT un_codigo_produto UNIQUE (codigo)

);

INSERT INTO produto_material (codigo, descricao, unidade, ativo,deleted) 
						VALUES ('PRE.BC-001','PRE ANEL 27MM X 19 A 20MM', 'PC', true, false);
INSERT INTO produto_material (codigo, descricao, unidade, ativo,deleted) 
						VALUES ('PRE.BC-002','PRE ANEL 20MM X 11 A 12MM', 'PC', true, false);
INSERT INTO produto_material (codigo, descricao, unidade, ativo,deleted) 
						VALUES ('BC-001','ANEL SEXTAVADO 19 A 20MM C/ FURO 8,5MM', 'PC', true, false);
INSERT INTO produto_material (codigo, descricao, unidade, ativo,deleted) 
						VALUES ('BC-002','ANEL OITAVADO 19 A 20MM C/ FURO 10MM', 'PC', true, false);
INSERT INTO produto_material (codigo, descricao, unidade, ativo,deleted) 
						VALUES ('CHAPA-001','CHAPA 19 A 20MM', 'KG', true, false)


-- ############### Criar tabela ItemProdução

CREATE TABLE IF NOT EXISTS ficha_producao(

	idFichaProducao SERIAL NOT NULL,
	dataRetirada DATE,
	dataDevolucao DATE,
	motivoDevolucao VARCHAR (500),
	idFerramenta INT NOT NULL,
	idFuncionario INT NOT NULL,
	ativo boolean NOT NULL default true,
	
	CONSTRAINT pk_idFichaProducao PRIMARY KEY (idFichaProducao),
	CONSTRAINT fk_idFerramenta_ficha FOREIGN KEY (idFerramenta) REFERENCES ferramenta,
	CONSTRAINT fk_idFuncionario_ficha FOREIGN KEY (idFuncionario) REFERENCES funcionario

);

INSERT INTO ficha_producao (idFerramenta, idFuncionario) VALUES (1,1);
INSERT INTO ficha_producao (idFerramenta, idFuncionario) VALUES (3,2);
INSERT INTO ficha_producao (idFerramenta, idFuncionario) VALUES (5,3);
						
-- ############### Criar tabela ItemProdução

CREATE TABLE IF NOT EXISTS item_producao (
						
	idItemProducao SERIAL NOT NULL,
	dta DATE NOT NULL,
	idFichaProducao INT NOT NULL,
	idFuncionario INT NOT NULL,
	idProdutoMaterial INT NOT NULL,
	quantidade DECIMAL NOT NULL,

	CONSTRAINT pk_idItemProducao PRIMARY KEY (idItemProducao),
	CONSTRAINT fk_idFichaProducao_item FOREIGN KEY (idFichaProducao) REFERENCES ficha_producao,
	CONSTRAINT fk_idFuncionario_item FOREIGN KEY (idFuncionario) REFERENCES funcionario,
	CONSTRAINT fk_idProdutoMaterial_item FOREIGN KEY (idProdutoMaterial) REFERENCES produto_material
	
);

INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('02/05/2018', 1 , 3 , 3, 100);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('03/05/2018', 1 , 3 , 3, 100);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('04/04/2018', 1 , 3 , 5, 100.350);
				   
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('02/05/2018', 2 , 3 , 3, 200);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('03/05/2018', 2 , 3 , 3, 250);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('04/04/2018', 2 , 3 , 5, 150.350);
				   
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('02/05/2018', 3 , 3 , 3, 30);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('03/05/2018', 3 , 3 , 3, 35);
INSERT INTO item_producao (dta, idFichaProducao, idFuncionario, idProdutoMaterial, quantidade) 
				   VALUES ('04/04/2018', 3 , 3 , 5, 200.350)