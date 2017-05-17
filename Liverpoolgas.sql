Use Master
GO

drop Database Liverpoolgas
GO

Create Database Liverpoolgas
On Primary
(
	NAME       = "Liverpoolgas_datos",
	FILENAME   = "C:\Program Files\Microsoft SQL Server\MSSQL12.SQL\MSSQL\DATA\Liverpoolgas_datos.Mdf",
	SIZE       = 10Mb,
	MAXSIZE    = 20Mb,
	FILEGROWTH = 2Mb
)
Log On
(
	NAME       = "Liverpoolgas_Log",
	FILENAME   = "C:\Program Files\Microsoft SQL Server\MSSQL12.SQL\MSSQL\DATA\Liverpoolgas_datos.Ldf",
	SIZE       = 5Mb,
	MAXSIZE    = 10Mb,
	FILEGROWTH = 10%
)
GO

/*CREATE ENTITIES*/
Use Liverpoolgas
GO
CREATE TABLE usuario
(
	cod_user integer primary key identity(1,1) not null,
	username varchar(20),
	pasword  varchar(20)
)
GO

CREATE TABLE cliente
(
	cod_cliente integer primary key identity(1,1) not null,
	nombres_cliente varchar(20),
	apellidop_cliente varchar(20),
	apellidom_cliente varchar(20),
	sexo_cliente varchar(15),
	activo int not null
)
GO

	
CREATE TABLE empleado
(
	cod_empleado integer primary key identity(1,1) not null,
	nombres_empleado varchar(20),
	apellidop_empleado varchar(20),
	apellidom_empleado varchar(20),
	sexo_empleado varchar(15),
	activo int not null
)
GO

CREATE TABLE producto
(
	cod_producto integer primary key identity(1,1) not null,
	nombre_producto varchar(30),
	precio_producto float(53),
	cantidad_producto integer,
	descripcion_producto varchar(50),
	activo integer not null
)
GO

CREATE TABLE departamento
(
	cod_departamento integer primary key identity(1,1) not null,
	nombre_departamento varchar(50)
)
GO

CREATE TABLE proveedor
(
	cod_proveedor integer primary key identity(1,1) not null,
	nombre_proveedor varchar(20),
	apellidop_proveedor varchar(20),
	apellidom_proveedor varchar(20),
	activo integer not null
)
GO

CREATE TABLE domicilio
(
	cod_domicilio integer primary key identity(1,1) not null,
	calle varchar(20),
	num_int varchar(4),
	num_ext integer,
	descripcion_domicilio varchar(40)
)
GO

CREATE TABLE correo
(
	cod_correo integer primary key identity(1,1) not null,
	nombre_correo varchar(40),
	descripcion_correo varchar(40)
)
GO

CREATE TABLE telefono
(
	cod_telefono integer primary key identity(1,1) not null,
	numero_telefono varchar(25),
	descripcion_telefono varchar(30)
)
GO

CREATE TABLE horario
(
	cod_horario integer primary key identity(1,1) not null,
	hora_entrada varchar(20),
	hora_salida varchar(20)
)
GO

CREATE TABLE modelo
(
	cod_modelo integer primary key identity(1,1) not null,
	nombre_modelo varchar(20)
)
GO

CREATE TABLE color
(
	cod_color integer primary key identity(1,1) not null,
	nombre_color varchar(20)
)
GO

CREATE TABLE colonia
(
	cod_colonia integer primary key identity(1,1) not null,
	nombre_colonia varchar(20),
	cp integer
)
GO

CREATE TABLE estado
(
	cod_estado integer primary key identity(1,1) not null,
	nombre_estado varchar(20)
)
GO

CREATE TABLE ciudad
(
	cod_ciudad integer primary key identity(1,1) not null,
	nombre_ciudad varchar(20)
)
GO

CREATE TABLE dia
(
	cod_dia integer primary key identity(1,1) not null,
	nombre_dia varchar(20)
)
GO

CREATE TABLE marca
(
	cod_marca integer primary key identity(1,1) not null,
	nombre_marca varchar(20),
	activo integer not null
)
GO


/*CREATE RELATIONSHIP*/

CREATE TABLE venta
(
	cod_venta integer primary key identity(1,1) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	folio_venta varchar (15),
	fecha_venta  date,
	precio_venta float(53),
	cantidad_venta integer
)
GO

CREATE TABLE compra
(
	cod_compra integer primary key identity(1,1) not null,
	cod_proveedor integer foreign key references proveedor(cod_proveedor) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	folio_compra varchar(15),
	fecha_compra date,
	precio_compra float(53),
	cantidad_compra integer
)
GO

CREATE TABLE promocion
(
	cod_promocion integer primary key identity(1,1) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	cod_departamento integer foreign key references departamento(cod_departamento) not null,
	fechaf_promocion date,
	fechai_promocion date,
	descripcion_promocion varchar(50),
	activo integer not null
)
GO

CREATE TABLE promo_venta
(
	cod_promo_venta integer primary key identity(1,1) not null,
	folio_venta varchar(15) not null,
	cod_producto integer not null
)
GO

CREATE TABLE producto_departamento
(
	cod_prod_depto integer primary key identity(1,1) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	cod_departamento integer foreign key references departamento(cod_departamento) not null
)
GO

CREATE TABLE cambio
(
	cod_cambio integer primary key identity(1,1) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null,
	folio_cambio varchar(15),
	diferencia float(53),
	nuevop_cambio varchar(50),
	fecha_cambio date
)
GO

CREATE TABLE estado_cuenta
(
	cod_cliente integer foreign key references cliente(cod_cliente) not null,
	folio_venta varchar(15) not null,
	fecha_limite_ec date,
	abono_ec float(53),
	total_ec float(53),
	fecha_abono date
)
GO

CREATE TABLE devolucion
(
	cod_devolucion integer primary key identity(1,1) not null,
	cod_producto integer foreign key references producto(cod_producto) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null,
	fecha_devolucion date,
	folio_devolucion varchar(15),
	monto_devolucion float(53)
)
GO

CREATE TABLE emp_departamento
(
	cod_emp_depto integer primary key identity(1,1) not null,
	cod_departamento integer foreign key references departamento(cod_departamento) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null
)
GO

CREATE TABLE dom_cliente
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null
)
GO

CREATE TABLE dom_empleado
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null
)
GO

CREATE TABLE dom_proveedor
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_proveedor integer foreign key references proveedor(cod_proveedor) not null
)
GO

CREATE TABLE correo_cliente
(
	cod_correo integer foreign key references correo(cod_correo) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null
)
GO

CREATE TABLE correo_empleado
(
	cod_correo integer foreign key references correo(cod_correo) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null
)
GO

CREATE TABLE correo_proveedor
(
	cod_correo integer foreign key references correo(cod_correo) not null,
	cod_proveedor integer foreign key references proveedor(cod_proveedor) not null
)
GO

CREATE TABLE telefono_cliente
(
	cod_telefono integer foreign key references telefono(cod_telefono) not null,
	cod_cliente integer foreign key references cliente(cod_cliente) not null
)
GO

CREATE TABLE telefono_empleado
(
	cod_telefono integer foreign key references telefono(cod_telefono) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null
)
GO

CREATE TABLE telefono_proveedor
(
	cod_telefono integer foreign key references telefono(cod_telefono) not null,
	cod_proveedor integer foreign key references proveedor(cod_proveedor) not null
)
GO

CREATE TABLE horario_empleado
(
	cod_horario integer foreign key references horario(cod_horario) not null,
	cod_empleado integer foreign key references empleado(cod_empleado) not null
)
GO

CREATE TABLE modelo_producto
(
	cod_modelo integer foreign key references modelo(cod_modelo) not null,
	cod_producto integer foreign key references producto(cod_producto) not null
)
GO

CREATE TABLE color_producto
(
	cod_color integer foreign key references color(cod_color) not null,
	cod_producto integer foreign key references producto(cod_producto) not null
)
GO

CREATE TABLE colonia_domicilio
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_colonia integer foreign key references colonia(cod_colonia) not null
)
GO

CREATE TABLE estado_domicilio
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_estado integer foreign key references estado(cod_estado) not null
)
GO

CREATE TABLE ciudad_domicilio
(
	cod_domicilio integer foreign key references domicilio(cod_domicilio) not null,
	cod_ciudad integer foreign key references ciudad(cod_ciudad) not null
)
GO

CREATE TABLE dia_horario
(
	cod_dia integer foreign key references dia(cod_dia) not null,
	cod_horario integer foreign key references horario(cod_horario) not null
)
GO

CREATE TABLE marca_producto
(
	cod_marca integer foreign key references marca(cod_marca) not null,
	cod_producto integer foreign key references producto(cod_producto) not null
)
GO

/* PROCEDIMIENTOS ALMACENADOS*/

/*agregar usuarios*/
go
create proc addUser(@username varchar(20) , @pasword varchar(20))
as begin
insert into usuario values(@username , @pasword)
end
go

/*eliminar usuarios*/
go
create proc deleteUser(@cod_user int)
as begin
delete from usuario where cod_user=@cod_user
end
go

/*buscar usuarios*/
go
create proc searchUser(@username varchar(20) , @password varchar(20))
as begin
select * from usuario where username=@username and pasword=@password
end
go

/*agregar en tabla clientes*/
go
create proc addClient( @nombresCliente varchar(20) , @appCliente varchar(20) , @apmCliente varchar(20) , @sexoCliente varchar(20) )
as begin
insert into cliente(nombres_cliente , apellidop_cliente , apellidom_cliente , sexo_cliente ,activo) values(@nombresCliente , @appCliente , @apmCliente , @sexoCliente , 1)
end
go

/*agregar en tabla domicilios*/
go
create proc addDom( @calle varchar(20) , @nInt varchar(4) , @nExt int , @desc varchar(40) )
as begin
insert into domicilio(calle , num_int , num_ext , descripcion_domicilio) values(@calle , @nInt , @nExt , @desc)
end 
go

/*agregar en estado*/
go
create proc addEstado( @estado varchar(20) )
as begin
insert into estado(nombre_estado) values(@estado)
end
go

/*agregar en ciudad*/
go
create proc addCiudad( @ciudad varchar(20) )
as begin
insert into ciudad(nombre_ciudad) values(@ciudad)
end
go

/*agregar en colonias*/
go
create proc addColonia(@colonia varchar(20) , @cp int)
as begin
insert into colonia(nombre_colonia , cp) values(@colonia , @cp)
end
go

/*agregar en telefonos*/
go
create proc addTel( @tel varchar(25) , @descTel varchar(30) )
as begin
insert into telefono(numero_telefono , descripcion_telefono) values(@tel , @descTel)
end
go

/*agregar en correos*/
go
create proc addEmail( @correo varchar(40) , @descCorreo varchar(40) )
as begin
insert into correo(nombre_correo , descripcion_correo) values(@correo , @descCorreo)
end
go

/*agregar en dom_cliente*/
go
create proc addDomCte( @codDom int , @codCte int )
as begin
insert into dom_cliente(cod_domicilio , cod_cliente) values(@codDom , @codCte)
end
go

/*agregar en dom_colonia*/
go
create proc addDomColonia( @codDom int , @codColonia int )
as begin
insert into colonia_domicilio(cod_domicilio , cod_colonia) values(@codDom , @codColonia)
end
go

/*agregar en estado_domicilio*/
go
create proc addDomEstado( @codDom int , @codEstado int )
as begin
insert into estado_domicilio(cod_domicilio , cod_estado) values(@codDom , @codEstado)
end
go

/*agregar en ciudad_domicilio*/
go
create proc addDomCiudad( @codDom int , @codCiudad int )
as begin
insert into ciudad_domicilio(cod_domicilio , cod_ciudad) values(@codDom , @codCiudad)
end
go

/*agregar en telefono_cliente*/
go
create proc addTelCte( @codTel int , @codCte int )
as begin
insert into telefono_cliente(cod_telefono , cod_cliente) values(@codTel , @codCte)
end
go

/*agregar en correo_cliente*/
go
create proc addEmailCte( @codCorr int , @codCte int )
as begin
insert into correo_cliente(cod_correo , cod_cliente) values(@codCorr , @codCte)
end
go

/*eliminación lógica de un cliente*/
go
create proc deleteCte(@cod int)
as begin
update cliente set activo=0 where cod_cliente=@cod  
end
go

/*eliminación lógica de un empleado*/
go
create proc deleteEmpleado(@cod int)
as begin
update empleado set activo=0 where cod_empleado=@cod  
end
go

/*actualizar cliente*/
go
create proc updateCte( @nombre varchar(20) , @app varchar(20) , @apm varchar(20) , @sexo varchar(20) , @cod int )
as begin
update cliente set nombres_cliente=@nombre , apellidop_cliente=@app , apellidom_cliente=@apm , sexo_cliente=@sexo where cod_cliente=@cod
end
go

/*actualizar empleado*/
go
create proc updateEmpleado( @nombre varchar(20) , @app varchar(20) , @apm varchar(20) , @sexo varchar(20) , @cod int )
as begin
update empleado set nombres_empleado=@nombre , apellidop_empleado=@app , apellidom_empleado=@apm , sexo_empleado=@sexo where cod_empleado=@cod
end
go

/*eliminar telefono_cliente*/
go
create proc deleteTel_Cte(@cod int)
as begin
delete from telefono_cliente where cod_telefono=@cod
end
go

/*eliminar telefono_empleado*/
go
create proc deleteTel_Empleado(@cod int)
as begin
delete from telefono_empleado where cod_telefono=@cod
end
go

/*eliminar un telefono*/
go
create proc deleteTel(@cod int)
as begin
delete from telefono where cod_telefono=@cod
end
go

/*actualizar un teléfono*/
go
create proc updateTel(@tel varchar(25) , @desc varchar(30) , @cod int)
as begin
update telefono set numero_telefono=@tel , descripcion_telefono=@desc where cod_telefono=@cod
end
go


/*eliminar correo_cliente*/
go
create proc deleteEmail_Cte(@cod int)
as begin
delete from correo_cliente where cod_correo=@cod
end
go

/*eliminar correo_empleado*/
go
create proc deleteEmail_Empleado(@cod int)
as begin
delete from correo_empleado where cod_correo=@cod
end
go

/*eliminar un correo*/
go
create proc deleteEmail(@cod int)
as begin
delete from correo where cod_correo=@cod
end
go

/*actualizar un correo*/
go
create proc updateEmail(@correo varchar(40) , @desc varchar(40) , @cod int)
as begin
update correo set nombre_correo=@correo , descripcion_correo=@desc where cod_correo=@cod
end
go

/*modificar un domicilio completo*/
go
create proc updateDom( @cod int , @calle varchar(20) , @colonia varchar(20) , @num_int varchar(4) , @num_ext int , @cp int , @desc varchar(40) , @estado varchar(20) , @ciudad varchar(20) )
as begin
update domicilio set calle=@calle , 
					 num_int=@num_int,
					 num_ext=@num_ext,
					 descripcion_domicilio=@desc
				 where cod_domicilio=@cod

update colonia set nombre_colonia=@colonia ,cp=@cp where cod_colonia=@cod
update estado set nombre_estado=@estado where cod_estado=@cod
update ciudad set nombre_ciudad=@ciudad where cod_ciudad=@cod
end
go

/*Eliminar un domicilio completo*/
go
create proc deleteDom( @cod int )
as begin
delete from colonia_domicilio where cod_domicilio=@cod
delete from estado_domicilio where cod_domicilio=@cod
delete from ciudad_domicilio where cod_domicilio=@cod
delete from dom_cliente where cod_domicilio=@cod
delete from domicilio where cod_domicilio=@cod
delete from colonia where cod_colonia=@cod
delete from estado where cod_estado=@cod
delete from ciudad where cod_ciudad=@cod
end
go

/*Eliminar un domicilio completo del empleado*/
go
create proc deleteDomEmpleado( @cod int )
as begin
delete from colonia_domicilio where cod_domicilio=@cod
delete from estado_domicilio where cod_domicilio=@cod
delete from ciudad_domicilio where cod_domicilio=@cod
delete from dom_empleado where cod_domicilio=@cod
delete from domicilio where cod_domicilio=@cod
delete from colonia where cod_colonia=@cod
delete from estado where cod_estado=@cod
delete from ciudad where cod_ciudad=@cod
end
go

/*Agregar en departamento*/
go
create proc addDepa( @nombre varchar(50) )
as begin
insert into departamento( nombre_departamento ) values( @nombre  )
end
go

/*Agregar en producto*/
go
create proc addProd( @nombre varchar(30) , @precio float(53) , @cantidad int , @desc varchar(50) )
as begin
insert into producto( nombre_producto , precio_producto , cantidad_producto , descripcion_producto , activo ) values( @nombre , @precio , @cantidad , @desc , 1 )
end
go

/*Agregar en marca*/
go
create proc addMarca( @marca varchar(20) )
as begin
insert into marca(nombre_marca ,activo) values(@marca ,1)
end
go

/*Agregar en modelo*/
go
create proc addModelo( @modelo varchar(20) )
as begin
insert into modelo(nombre_modelo) values(@modelo)
end
go

/*Agregar en color*/
go
create proc addColor( @color varchar(20) )
as begin
insert into color(nombre_color) values(@color)
end
go

/*Agregar en producto_departamento*/
go
create proc addProducto_departamento(@cod_prod int , @cod_depa int)
as begin
insert into producto_departamento(cod_producto , cod_departamento) values(@cod_prod , @cod_depa)
end
go

/*Agregar en marca_producto*/
go
create proc addMarca_producto(@cod_marca int , @cod_prod int)
as begin
insert into marca_producto(cod_marca , cod_producto) values(@cod_marca , @cod_prod)
end
go

/*Agregar en modelo_producto*/
go
create proc addModelo_producto(@cod_modelo int , @cod_prod int)
as begin
insert into modelo_producto(cod_modelo , cod_producto) values(@cod_modelo , @cod_prod)
end
go

/*Agregar en color_producto*/
go
create proc addColor_producto(@cod_color int , @cod_prod int)
as begin
insert into color_producto(cod_color , cod_producto) values(@cod_color , @cod_prod)
end
go

/*Actualizar un producto completo*/
go
create proc updateProd( @cod int , @nombre varchar(30) , @precio float(53) , @cantidad int , @desc varchar(50) , @modelo varchar(20) , @color varchar(20) )
as begin
update producto set nombre_producto=@nombre , precio_producto=@precio , cantidad_producto=@cantidad , descripcion_producto=@desc where cod_producto=@cod
update modelo set nombre_modelo=@modelo where cod_modelo=@cod
update color set nombre_color=@color where cod_color=@cod
end
go

/*eliminación lógica de un producto*/
go
create proc deleteProd(@cod int)
as begin
update producto set activo=0 where cod_producto=@cod  
end
go

/*eliminación lógica de una marca*/
go
create proc deleteMarca(@cod int)
as begin
update marca set activo=0 where cod_marca=@cod  
end
go

/*habilitar una marca*/
go
create proc enableMarca(@cod int)
as begin
update marca set activo=1 where cod_marca=@cod  
end
go

/*habilitar un producto*/
go
create proc enableProd(@cod int)
as begin
update producto set activo=1 where cod_producto=@cod  
end
go

/*habilitar un cliente*/
go
create proc enableCliente(@cod int)
as begin
update cliente set activo=1 where cod_cliente=@cod  
end
go

/*habilitar un empleado*/
go
create proc enableEmpleado(@cod int)
as begin
update empleado set activo=1 where cod_empleado=@cod  
end
go

/*actualizar una marca*/
go
create proc updateMarca( @cod int , @marca varchar(20))
as begin
update marca set nombre_marca=@marca where cod_marca=@cod
end
go

/*actualizar una marca_producto*/
go
create proc updateMarcaProd( @codmarca int , @codprod int)
as begin
update marca_producto set cod_marca=@codmarca where cod_producto=@codprod
end
go

/*agregar en tabla empleados*/
go
create proc addEmpleado( @nombre varchar(20) , @app varchar(20) , @apm varchar(20) , @sexo varchar(20) )
as begin
insert into empleado(nombres_empleado , apellidop_empleado , apellidom_empleado , sexo_empleado ,activo) values(@nombre , @app , @apm , @sexo , 1)
end
go

/*agregar en empleado-depa*/
go
create proc addEmpleado_departamento(@cod_emp int , @cod_depa int)
as begin
insert into emp_departamento(cod_empleado , cod_departamento) values(@cod_emp , @cod_depa)
end
go

/*agregar en dom_empleado*/
go
create proc addDomEmpleado( @codDom int , @codEmp int )
as begin
insert into dom_empleado(cod_domicilio , cod_empleado) values(@codDom , @codEmp)
end
go

/*agregar en telefono_empleado*/
go
create proc addTelEmp( @codTel int , @codEmp int )
as begin
insert into telefono_empleado(cod_telefono , cod_empleado) values(@codTel , @codEmp)
end
go

/*agregar en correo_empleado*/
go
create proc addEmailEmp( @codCorr int , @codEmp int )
as begin
insert into correo_empleado(cod_correo , cod_empleado) values(@codCorr , @codEmp)
end
go

/*Agregar en dia*/
go
create proc addDia( @dia varchar(20) )
as begin
insert into dia(nombre_dia) values(@dia)
end
go

/*Agregar en horario*/
go
create proc addHorario( @entrada varchar(20) , @salida varchar(20) )
as begin
insert into horario(hora_entrada , hora_salida) values(@entrada , @salida)
end
go

/*agregar en dia_horario*/
go
create proc addDiaHorario( @codDia int , @codHorario int )
as begin
insert into dia_horario(cod_dia , cod_horario) values(@codDia , @codHorario)
end
go

/*eliminar en dia_horario*/
go
create proc deleteDiaHorario( @cod int )
as begin
delete from dia_horario where cod_dia=@cod
end
go 

/*eliminar en dia*/
go
create proc deleteDia( @cod int )
as begin
delete from dia where cod_dia=@cod
end
go

/*eliminar en horario*/
go
create proc deleteHorario( @cod int )
as begin
delete from horario where cod_horario=@cod
end
go

/*agregar en horario_empleado*/
go
create proc addHorarioEmpleado( @codHorario int , @codEmp int )
as begin
insert into horario_empleado(cod_horario , cod_empleado) values(@codHorario , @codEmp)
end
go 

/*eliminar en horario_empleado*/
go
create proc deleteHorarioEmpleado( @cod int )
as begin
delete from horario_empleado where cod_horario=@cod
end
go

/*actualizar en dia*/
go
create proc updateDia( @dia varchar(20) , @cod int )
as begin
update dia set nombre_dia=@dia where cod_dia=@cod
end
go

/*actualizar en horario*/
go
create proc updateHorario( @entrada varchar(20) , @salida varchar(20) , @cod int )
as begin
update horario set hora_entrada=@entrada , hora_salida=@salida where cod_horario=@cod
end
go


/*Provedores aqui =>*/
/*Agregar en tabla proveedores*/
go
create proc addProv(@nombresProveedor varchar(20) ,@appProveedor varchar(20) ,@apmProveedor varchar(20) )
as begin
insert into proveedor(nombre_proveedor , apellidop_proveedor , apellidom_proveedor, activo) values (@nombresProveedor, @appProveedor, @apmProveedor, 1)
end
go

/*Agregar en dom_proveedor*/
go
create proc addDomProv(@codDom int , @codProv int)
as begin
insert into dom_proveedor(cod_domicilio , cod_proveedor) values(@codDom , @codProv)
end
go


/*agregar en telefono_proveedor*/
go
create proc addTelProv(@codTel int , @codProv int)
as begin 
insert into telefono_proveedor(cod_telefono , cod_proveedor) values(@codTel , @codProv)
end 
go


/*agregar en correo_proveedor*/
go
create proc addEmailProv(@codCorreo int, @codProv int)
as begin
insert into correo_proveedor(cod_correo , cod_proveedor) values(@codCorreo , @codProv)
end 
go

/*eliminación lógica de un proveedor*/
go
create proc deleteProv(@cod int)
as begin
update proveedor set activo=0 where cod_proveedor=@cod  
end
go

/*actualizar proveedor*/
go
create proc updateProv( @nombre varchar(20) , @app varchar(20) , @apm varchar(20) , @cod int )
as begin
update proveedor set nombre_proveedor=@nombre , apellidop_proveedor=@app , apellidom_proveedor=@apm where cod_proveedor=@cod
end
go

/*eliminar telefono_proveedor*/
go
create proc deleteTel_Prov(@cod int)
as begin
delete from telefono_proveedor where cod_telefono=@cod
end
go

/*eliminar correo_proveedor*/
go
create proc deleteEmail_Prov(@cod int)
as begin
delete from correo_proveedor where cod_correo=@cod
end
go

/*Habilitar un proveedor*/
go
create proc enableProveedor(@cod int)
as begin
update proveedor set activo=1 where cod_proveedor=@cod  
end
go

/*:::::PROMOCION:::::*/
/*insertar promocion*/
go
create proc nuevaPromo( @codProd int , @codDepa int , @fechai varchar(10) , @fechaf varchar(10) , @desc varchar(50) )
as begin
insert into promocion( cod_producto , cod_departamento , fechai_promocion , fechaf_promocion , descripcion_promocion , activo ) values( @codProd , @codDepa , convert(date , @fechai) , convert(date , @fechaf) , @desc , 1 )
end
go

/*deshabilitar promo*/
go
create proc disabledPromo( @cod int )
as begin
update promocion set activo=0 where cod_promocion=@cod
end
go

/*habilitar promo*/
go
create proc enabledPromo( @codPromo int , @codProd int , @codDepa int , @fechai varchar(10) , @fechaf varchar(10) , @desc varchar(50) )
as begin
update promocion set cod_producto=@codProd , cod_departamento=@codDepa , fechai_promocion=convert(date , @fechai) , fechaf_promocion=convert(date , @fechaf) , descripcion_promocion=@desc , activo=1 where cod_promocion=@codPromo
end
go

/*modificar promocion*/
go
create proc updatePromo(@codPromo int , @codProd int , @codDepa int , @fechai varchar(10) , @fechaf varchar(10) , @desc varchar(50) )
as begin
update promocion set cod_producto=@codProd , cod_departamento=@codDepa , fechai_promocion=convert(date , @fechai) , fechaf_promocion=convert(date , @fechaf) , descripcion_promocion=@desc where cod_promocion=@codPromo
end
go

/*agregar el folio a promo_venta para ser relacionado al mostrar venta*/
go
create proc addPromo_venta( @cod_prod int , @folio varchar(15) )
as begin
INSERT INTO promo_venta(cod_producto , folio_venta ) values(@cod_prod , @folio)
end
go

/*:::::VENTAS:::::*/
/*insertar venta*/
go
create proc nuevaVenta( @codEmp int , @codCte int , @codProd int , @folio varchar(15) , @fecha varchar(10) , @precio float(53) , @cantidad int )
as begin
insert into venta( cod_empleado , cod_cliente , cod_producto , folio_venta , fecha_venta , precio_venta , cantidad_venta ) values( @codEmp , @codCte , @codProd , @folio , convert(date , @fecha) , @precio , @cantidad )
end
go

/*restar producto cuando se realiza venta*/
go
create proc restPxV( @cod int , @cant int )
as begin
update producto set cantidad_producto=@cant where cod_producto=@cod
end
go



/*Some querys*/
select * from cliente where nombres_cliente like '%dun%' and activo=0
select * from domicilio
select * from colonia
select * from telefono
select * from correo
select * from estado
select * from ciudad
select * from dom_cliente
select * from colonia_domicilio
select * from estado_domicilio
select * from ciudad_domicilio
select * from telefono_cliente
select * from correo_cliente

select * from departamento
select * from producto
select * from marca
select * from modelo
select * from color
select * from producto_departamento
select * from marca_producto
select * from modelo_producto
select * from color_producto

select * from empleado
select * from departamento
select * from domicilio
select * from colonia
select * from estado
select * from ciudad
select * from telefono
select * from correo
select * from dia_horario
select * from horario_empleado
select * from horario	

update producto set activo=1 where cod_producto=1

SELECT calle , nombre_colonia , num_ext , num_int , cp , descripcion_domicilio , nombre_estado , nombre_ciudad
          FROM domicilio
          INNER JOIN dom_cliente ON domicilio.cod_domicilio=dom_cliente.cod_domicilio AND
          dom_cliente.cod_cliente=1
          INNER JOIN colonia_domicilio ON dom_cliente.cod_domicilio=colonia_domicilio.cod_domicilio
          INNER JOIN colonia ON colonia_domicilio.cod_colonia=colonia.cod_colonia
          INNER JOIN estado_domicilio ON dom_cliente.cod_domicilio=estado_domicilio.cod_domicilio
          INNER JOIN estado ON estado_domicilio.cod_estado=estado.cod_estado
          INNER JOIN ciudad_domicilio ON dom_cliente.cod_domicilio=ciudad_domicilio.cod_domicilio
          INNER JOIN ciudad ON ciudad_domicilio.cod_ciudad=ciudad.cod_ciudad

select nombre_correo , descripcion_correo from correo
                        inner join correo_cliente on correo.cod_correo=correo_cliente.cod_correo
                        and correo_cliente.cod_cliente=2


insert into correo(nombre_correo , descripcion_correo) values('pancho@gmail.com', 'correo')
go

drop database fecha
go
use master
go
create database fecha
go
use fecha
create table dat(
	fec date
)
go
insert into dat(fec) values(convert(date,'2008-08-24'))
go

select * from dat order by fec
go

select * from venta inner join producto on venta.cod_producto=producto.cod_producto 
inner join marca_producto on producto.cod_producto=marca_producto.cod_producto
inner join marca on marca_producto.cod_marca=marca.cod_marca
inner join cliente on venta.cod_cliente=cliente.cod_cliente
inner join empleado on venta.cod_empleado=empleado.cod_empleado
where venta.folio_venta='1754531471' 
go

SELECT * FROM promocion inner join producto on promocion.cod_producto=producto.cod_producto and promocion.activo=1 order by fechai_promocion
go

SELECT COUNT(folio_venta), folio_venta FROM venta GROUP BY folio_venta order by folio_venta ASC
go

select * from promocion
go

update promocion set folio_venta = 'asdfgh' where cod_promocion=3
go

select * from promo_venta
go