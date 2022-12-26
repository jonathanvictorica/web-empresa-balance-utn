
-- drop DATABASE INVERSIONES_DESARROLLO_2;
-- drop  USER 'usr_inversiones'@'localhost';



CREATE DATABASE INVERSIONES_DESARROLLO_2;
USE INVERSIONES_DESARROLLO_2;

CREATE USER 'usr_inversiones'@'localhost' IDENTIFIED BY 'usr_inversiones';

grant all on INVERSIONES_DESARROLLO.* to 'usr_inversiones'@'localhost' ;

USE INVERSIONES_DESARROLLO;

CREATE TABLE INV_0012_Usuario(
       id bigint auto_increment primary key,
       nick varchar(60) not null unique,
       clave text(300) not null,
       nombre varchar(60) not null,
       apellido varchar(60) not null	
);

insert into INV_0012_Usuario values(NULL,'Hector','123','Hector','Rodriguez');
insert into INV_0012_Usuario values(NULL,'Jonathan','123','Jonathan','Victorica');
insert into INV_0012_Usuario values(NULL,'Tomas','123','Tomas','Rodriguez');
insert into INV_0012_Usuario values(NULL,'Rodrigo','123','Rodrigo','Altamonte');
insert into INV_0012_Usuario values(NULL,'Guillermo','123','Guillermo','Fernandez');

update INV_0012_Usuario set clave = SHA2(clave, 256);

CREATE TABLE INV_0001_Empresa(
     id bigint auto_increment primary key,
	 cuit varchar(12) not null unique,
	 razon_Social varchar(100) not null ,
	 descrip_Actividad varchar(120) not null	,
     fecha_Alta date not null	 
);

CREATE TABLE INV_0003_Plan_Cuenta(
    id bigint auto_increment primary key,
	nombre varchar(60) not null unique,
	habilitado smallint default 1 CHECK (habilitado=1 OR habilitado=0)
);

CREATE TABLE INV_0002_Cuenta(
    id bigint auto_increment primary key,
	nombre varchar(60) not null unique,
	codigoCuenta varchar(6) not null unique,
	nivel smallint not null ,
	habilitado smallint default 1 CHECK (habilitado=1 OR habilitado=0)
);

CREATE TABLE INV_0004_Plan_Cuenta_Detalle(
     id bigint auto_increment primary key,
	 id_Plan bigint not null,
	 id_Cuenta bigint not null,
	 foreign key(id_Plan) references INV_0003_Plan_Cuenta(id) ON DELETE CASCADE,
	 foreign key(id_cuenta) references INV_0002_Cuenta(id) ,
	 unique(id_Plan,id_Cuenta)
);

CREATE TABLE INV_0005_Balance(
     id bigint auto_increment primary key,
	 id_Empresa bigint not null,
	 fecha_Comienzo date not null,
	 fecha_Cierre date not null,
	 id_Plan_Cuenta bigint not null,
     foreign key(id_Empresa) references INV_0001_Empresa(id) ON DELETE CASCADE,
	 foreign key(id_Plan_Cuenta) references INV_0003_Plan_Cuenta(id),
	 unique(id_Empresa,fecha_Comienzo)
);

CREATE TABLE INV_0006_Cuenta_Valor(
     id bigint auto_increment primary key,
	 id_Balance bigint not null,
	 id_Cuenta bigint not null,
	 valor numeric(15,2) not null default 0,
	 foreign key(id_balance) references INV_0005_Balance(id) ON DELETE CASCADE,
	 foreign key(id_Cuenta) references INV_0002_Cuenta(id),
	 unique(id_Balance,id_Cuenta)
);

CREATE TABLE INV_0007_Indicador(
      id bigint auto_increment primary key,
	  nombre varchar(60) not null ,
	  formula text(3000) not null ,
	  id_usuario bigint not null,
	  foreign key(id_usuario) references INV_0012_Usuario(id)  ON DELETE CASCADE,
	  unique(nombre,id_usuario)
);

CREATE TABLE INV_0008_Metodologia(
      id bigint auto_increment primary key,
	  nombre varchar(60) not null ,
	  id_usuario bigint not null,
	  foreign key(id_usuario) references INV_0012_Usuario(id)  ON DELETE CASCADE,
	  unique(nombre,id_usuario)	  
); 

CREATE TABLE INV_0013_Comparador(
      id integer auto_increment primary key,
      descripcion varchar(20)  not null unique,
      signo varchar(3) not null  unique
);

CREATE TABLE INV_0014_Tipo_Indicador_Economico(
      id integer auto_increment primary key,
      descripcion varchar(20) not null unique      
);

CREATE TABLE INV_0015_Tipo_Condicion(
      id integer auto_increment primary key,
      descripcion varchar(20) not null unique     
);

insert into INV_0013_Comparador values(NULL,'Mayor','>');
insert into INV_0013_Comparador values(NULL,'MENOR','<');
insert into INV_0013_Comparador values(NULL,'IGUAL','==');
insert into INV_0013_Comparador values(NULL,'MAYOR_IGUAL','>=');
insert into INV_0013_Comparador values(NULL,'MENOR_IGUAL','<=');

insert into INV_0015_Tipo_Condicion values(NULL,'TaxativasConValor');
insert into INV_0015_Tipo_Condicion values(NULL,'TaxativasConIndicador');

insert into INV_0014_Tipo_Indicador_Economico values(NULL,'Cuenta');
insert into INV_0014_Tipo_Indicador_Economico values(NULL,'Indicador');
insert into INV_0014_Tipo_Indicador_Economico values(NULL,'Valor Fijo');

CREATE TABLE INV_0009_Condicion_Taxativa(
     id_condicion  bigint auto_increment primary key,
	 nombre_Condicion varchar(60) not null,
	 id_metodologia bigint not null,
	 id_tipo_Indicador_Economico integer,
	 nombre_Indicador_Economico varchar(60) not null,
	 id_comparador integer,
	 id_tipo_Indicador_Economico_Comparar integer,
	 nombre_Indicador_Economico_Comparar varchar(60) null default null,
	 valor_Fijo numeric(15,2) null default null,   
	 id_tipo_Condicion integer,
	 foreign key(id_metodologia) references INV_0008_Metodologia(id) ON DELETE CASCADE,
	 foreign key(id_tipo_Condicion) references INV_0015_Tipo_Condicion(id) ,
	 foreign key(id_tipo_Indicador_Economico) references INV_0014_Tipo_Indicador_Economico(id),
	 foreign key(id_comparador) references INV_0013_Comparador(id),
	 foreign key(id_tipo_Indicador_Economico_Comparar) references INV_0014_Tipo_Indicador_Economico(id),   
     unique(nombre_Condicion,id_metodologia)	 
);

CREATE TABLE INV_0010_Condicion_Priorizable(
     id_condicion bigint auto_increment primary key,
	 nombre_Condicion varchar(60) not null,
	 id_metodologia bigint not null,		
	 foreign key(id_metodologia) references INV_0008_Metodologia(id) ON DELETE CASCADE,
     unique(nombre_Condicion,id_metodologia)		 
);

CREATE TABLE INV_0011_Condiciones_Taxativas_Relacionadas(
     id bigint auto_increment primary key,
     id_condicion bigint not null,
     id_condicion_Taxativa bigint not null,
     foreign key(id_condicion) references INV_0010_Condicion_Priorizable(id_condicion) ON DELETE CASCADE,
     foreign key(id_condicion_Taxativa) references INV_0009_Condicion_Taxativa(id_condicion) ON DELETE CASCADE,
     unique(id_condicion,id_condicion_Taxativa)				  
);






















