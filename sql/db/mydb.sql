-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`condutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`condutor` (
  `numero_CNH` CHAR(9) NOT NULL,
  `data_cnh` DATE NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `pontuacao_atual` SMALLINT(2) UNSIGNED NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  PRIMARY KEY (`numero_CNH`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`veiculo` (
  `placa` CHAR(7) NOT NULL,
  `cnh_propietario` CHAR(9) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  PRIMARY KEY (`placa`),
  INDEX `fk_veiculo_condutor1_idx` (`cnh_propietario` ASC) VISIBLE,
  CONSTRAINT `fk_veiculo_condutor1`
    FOREIGN KEY (`cnh_propietario`)
    REFERENCES `mydb`.`condutor` (`numero_CNH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`agente_fiscalizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`agente_fiscalizador` (
  `id_agente` CHAR(16) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  PRIMARY KEY (`id_agente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`autuacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autuacao` (
  `id_autuacao` CHAR(16) NOT NULL,
  `id_agente` CHAR(16) NOT NULL,
  `placa` CHAR(7) NOT NULL,
  `data_ocorrido` DATE NOT NULL,
  `valor` INT UNSIGNED NOT NULL,
  `pontuacao` SMALLINT(2) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  PRIMARY KEY (`id_autuacao`),
  INDEX `fk_autuacao_veiculo1_idx` (`placa` ASC) VISIBLE,
  INDEX `fk_autuacao_agente_fiscalizador1_idx` (`id_agente` ASC) VISIBLE,
  CONSTRAINT `fk_autuacao_veiculo1`
    FOREIGN KEY (`placa`)
    REFERENCES `mydb`.`veiculo` (`placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autuacao_agente_fiscalizador1`
    FOREIGN KEY (`id_agente`)
    REFERENCES `mydb`.`agente_fiscalizador` (`id_agente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`multa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`multa` (
  `id_multa` CHAR(16) NOT NULL,
  `id_autuacao_respondida` CHAR(16) NOT NULL,
  `cnh_responsavel` CHAR(9) NOT NULL,
  `valor` INT UNSIGNED NOT NULL,
  `pontuacao` SMALLINT(2) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  PRIMARY KEY (`id_multa`),
  INDEX `fk_multa_autuacao1_idx` (`id_autuacao_respondida` ASC) VISIBLE,
  INDEX `fk_multa_condutor1_idx` (`cnh_responsavel` ASC) VISIBLE,
  UNIQUE INDEX `id_autuacao_respondida_UNIQUE` (`id_autuacao_respondida` ASC) VISIBLE,
  CONSTRAINT `fk_multa_autuacao1`
    FOREIGN KEY (`id_autuacao_respondida`)
    REFERENCES `mydb`.`autuacao` (`id_autuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_multa_condutor1`
    FOREIGN KEY (`cnh_responsavel`)
    REFERENCES `mydb`.`condutor` (`numero_CNH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`infracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`infracao` (
  `id_infracao` CHAR(16) NOT NULL,
  `nome` VARCHAR(256) NOT NULL,
  `penalidade` SMALLINT(2) NOT NULL,
  `descricao` VARCHAR(1024) NULL,
  PRIMARY KEY (`id_infracao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`autuacao_infracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autuacao_infracao` (
  `id_autuacao` CHAR(16) NOT NULL,
  `id_infracao` CHAR(16) NOT NULL,
  PRIMARY KEY (`id_autuacao`, `id_infracao`),
  INDEX `fk_multa_infracao_infracao1_idx` (`id_infracao` ASC) VISIBLE,
  CONSTRAINT `fk_autuacao_infracao_infracao1`
    FOREIGN KEY (`id_infracao`)
    REFERENCES `mydb`.`infracao` (`id_infracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autuacao_infracao_autuacao1`
    FOREIGN KEY (`id_autuacao`)
    REFERENCES `mydb`.`autuacao` (`id_autuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`administrador` (
  `id_admin` CHAR(16) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  PRIMARY KEY (`id_admin`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
