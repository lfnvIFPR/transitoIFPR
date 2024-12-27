-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema transito
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema transito
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transito` ;
USE `transito` ;

-- -----------------------------------------------------
-- Table `transito`.`condutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`condutor` (
  `numero_CNH` CHAR(9) NOT NULL,
  `data_cnh` DATE NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `pontuacao_atual` SMALLINT(2) UNSIGNED NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  `hash_senha` VARCHAR(512) NOT NULL,
  `salt` CHAR(8) NOT NULL,
  PRIMARY KEY (`numero_CNH`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`veiculo` (
  `placa` CHAR(7) NOT NULL,
  `cnh_propietario` CHAR(9) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  PRIMARY KEY (`placa`),
  INDEX `fk_veiculo_condutor1_idx` (`cnh_propietario` ASC) VISIBLE,
  CONSTRAINT `fk_veiculo_condutor1`
    FOREIGN KEY (`cnh_propietario`)
    REFERENCES `transito`.`condutor` (`numero_CNH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`agente_fiscalizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`agente_fiscalizador` (
  `id_agente` CHAR(16) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  `hash_senha` VARCHAR(512) NOT NULL,
  `salt` CHAR(8) NOT NULL,
  PRIMARY KEY (`id_agente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`autuacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`autuacao` (
  `id_autuacao` CHAR(16) NOT NULL,
  `id_agente` CHAR(16) NOT NULL,
  `placa` CHAR(7) NOT NULL,
  `data_ocorrido` DATE NOT NULL,
  `valor` INT UNSIGNED NOT NULL,
  `pontuacao` SMALLINT(2) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  `respondido` TINYINT NOT NULL,
  PRIMARY KEY (`id_autuacao`),
  INDEX `fk_autuacao_veiculo1_idx` (`placa` ASC) VISIBLE,
  INDEX `fk_autuacao_agente_fiscalizador1_idx` (`id_agente` ASC) VISIBLE,
  CONSTRAINT `fk_autuacao_veiculo1`
    FOREIGN KEY (`placa`)
    REFERENCES `transito`.`veiculo` (`placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autuacao_agente_fiscalizador1`
    FOREIGN KEY (`id_agente`)
    REFERENCES `transito`.`agente_fiscalizador` (`id_agente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`multa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`multa` (
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
    REFERENCES `transito`.`autuacao` (`id_autuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_multa_condutor1`
    FOREIGN KEY (`cnh_responsavel`)
    REFERENCES `transito`.`condutor` (`numero_CNH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`infracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`infracao` (
  `id_infracao` CHAR(16) NOT NULL,
  `nome` VARCHAR(256) NOT NULL,
  `penalidade` SMALLINT(2) NOT NULL,
  `descricao` VARCHAR(1024) NULL,
  PRIMARY KEY (`id_infracao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`autuacao_infracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`autuacao_infracao` (
  `id_autuacao` CHAR(16) NOT NULL,
  `id_infracao` CHAR(16) NOT NULL,
  PRIMARY KEY (`id_autuacao`, `id_infracao`),
  INDEX `fk_multa_infracao_infracao1_idx` (`id_infracao` ASC) VISIBLE,
  CONSTRAINT `fk_autuacao_infracao_infracao1`
    FOREIGN KEY (`id_infracao`)
    REFERENCES `transito`.`infracao` (`id_infracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autuacao_infracao_autuacao1`
    FOREIGN KEY (`id_autuacao`)
    REFERENCES `transito`.`autuacao` (`id_autuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`administrador` (
  `id_admin` CHAR(16) NOT NULL,
  `hash_senha` VARCHAR(512) NOT NULL,
  `ultima_data_atividade` DATETIME NOT NULL,
  `salt` CHAR(8) NOT NULL,
  PRIMARY KEY (`id_admin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transito`.`resposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transito`.`resposta` (
  `id_resposta` CHAR(16) NOT NULL,
  `id_autuacao` CHAR(16) NOT NULL,
  `responsavel` CHAR(9) NOT NULL,
  `descricao` VARCHAR(512) NULL,
  PRIMARY KEY (`id_resposta`),
  INDEX `fk_resposta_autuacao1_idx` (`id_autuacao` ASC) VISIBLE,
  INDEX `fk_resposta_condutor1_idx` (`responsavel` ASC) VISIBLE,
  CONSTRAINT `fk_resposta_autuacao1`
    FOREIGN KEY (`id_autuacao`)
    REFERENCES `transito`.`autuacao` (`id_autuacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resposta_condutor1`
    FOREIGN KEY (`responsavel`)
    REFERENCES `transito`.`condutor` (`numero_CNH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
