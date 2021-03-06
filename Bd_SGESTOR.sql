/*    ==Parâmetros de Script==

    Versão do Servidor de Origem : SQL Server 2017 (14.0.1000)
    Edição do Mecanismo de Banco de Dados de Origem : Microsoft SQL Server Enterprise Edition
    Tipo do Mecanismo de Banco de Dados de Origem : SQL Server Autônomo

    Versão do Servidor de Destino : SQL Server 2017
    Edição de Mecanismo de Banco de Dados de Destino : Microsoft SQL Server Standard Edition
    Tipo de Mecanismo de Banco de Dados de Destino : SQL Server Autônomo
*/
USE [master]
GO
/****** Object:  Database [Sgestor_DB]    Script Date: 05/12/2017 11:22:39 ******/
CREATE DATABASE [Sgestor_DB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Sgestor_DB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Sgestor_DB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Sgestor_DB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Sgestor_DB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Sgestor_DB] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Sgestor_DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Sgestor_DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Sgestor_DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Sgestor_DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Sgestor_DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Sgestor_DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [Sgestor_DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Sgestor_DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Sgestor_DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Sgestor_DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Sgestor_DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Sgestor_DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Sgestor_DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Sgestor_DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Sgestor_DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Sgestor_DB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Sgestor_DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Sgestor_DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Sgestor_DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Sgestor_DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Sgestor_DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Sgestor_DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Sgestor_DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Sgestor_DB] SET RECOVERY FULL 
GO
ALTER DATABASE [Sgestor_DB] SET  MULTI_USER 
GO
ALTER DATABASE [Sgestor_DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Sgestor_DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Sgestor_DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Sgestor_DB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Sgestor_DB] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Sgestor_DB', N'ON'
GO
ALTER DATABASE [Sgestor_DB] SET QUERY_STORE = OFF
GO
USE [Sgestor_DB]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [Sgestor_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fc_aut]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[fc_aut] (@nome varchar(100),@senha varchar(100))
returns int
as
begin

declare @nome1 varchar(100), @bad int

select top 1 @nome1=nome from usuario
where nome = @nome and senha = @senha

	if @nome1 is null
		select @bad = 0
	else
		select @bad = 1
	
	return @bad	
end
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[id_cli] [int] IDENTITY(1,1) NOT NULL,
	[nome_cli] [varchar](50) NOT NULL,
	[email_cli] [varchar](50) NULL,
	[telefone_cli] [varchar](15) NULL,
	[celular_cli] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_cli] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[servico]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[servico](
	[id_ser] [int] IDENTITY(1,1) NOT NULL,
	[nome_ser] [varchar](45) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_ser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdemServico]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdemServico](
	[id_or] [int] IDENTITY(1,1) NOT NULL,
	[dataservico] [date] NOT NULL,
	[valor] [money] NOT NULL,
	[id_ser_or] [int] NOT NULL,
	[id_cli_or] [int] NOT NULL,
	[descricao] [varchar](300) NULL,
	[status_or] [bit] NULL,
	[data_fechamento] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_or] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[relatorio]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[relatorio](id,cliente,servico,valor,dataservico,status_or) 
as
select o.id_or,c.nome_cli,s.nome_ser,o.valor,o.dataservico,o.status_or from ordemservico o 
join Cliente c on  c.id_cli = o.id_cli_or
join servico s on s.id_ser = o.id_ser_or
GO
/****** Object:  Table [dbo].[usuario]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](45) NOT NULL,
	[senha] [varchar](20) NOT NULL,
	[perfil] [varchar](45) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[OrdemServico] ADD  DEFAULT (getdate()) FOR [dataservico]
GO
ALTER TABLE [dbo].[usuario] ADD  DEFAULT ((123)) FOR [senha]
GO
ALTER TABLE [dbo].[usuario] ADD  DEFAULT ('Funcionario') FOR [perfil]
GO
ALTER TABLE [dbo].[OrdemServico]  WITH CHECK ADD FOREIGN KEY([id_cli_or])
REFERENCES [dbo].[Cliente] ([id_cli])
GO
ALTER TABLE [dbo].[OrdemServico]  WITH CHECK ADD FOREIGN KEY([id_ser_or])
REFERENCES [dbo].[servico] ([id_ser])
GO
/****** Object:  StoredProcedure [dbo].[PR_FMNT]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[PR_FMNT]
@descricao varchar(200),
@cod int
as
if LEN(@descricao) < 2
	set @descricao = 'Serviço Pronto'
else
	update OrdemServico set descricao = @descricao
	where id_or = @cod
GO
/****** Object:  StoredProcedure [dbo].[PR_FMNT1]    Script Date: 05/12/2017 11:22:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[PR_FMNT1]
@descricao varchar(200),
@cod int
as
if @descricao is null
	set @descricao = 'Serviço Pronto'
else
	update OrdemServico set descricao = @descricao
	where id_or = @cod
GO
USE [master]
GO
ALTER DATABASE [Sgestor_DB] SET  READ_WRITE 
GO
