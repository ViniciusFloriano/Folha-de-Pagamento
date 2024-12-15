CREATE SCHEMA `folhapagamento` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `folhapagamento` ;

-- -----------------------------------------------------
-- Table `folhapagamento`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `tipo` ENUM('ADMINISTRADOR', 'FUNCIONARIO') NOT NULL,
  `status` ENUM('ATIVADO', 'DESATIVADO') NOT NULL,
  PRIMARY KEY (`id`)
  ) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `folhapagamento`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`funcionario` (
  `id` INT NOT NULL,
  `cargo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
   	FOREIGN KEY (`id`)
    REFERENCES `folhapagamento`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`funcionario_clt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`funcionario_clt` (
    `id` INT NOT NULL,
    `salario_mensal` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`funcionario_horista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`funcionario_horista` (
    `id` INT NOT NULL,
    `salario_hora` DECIMAL(10,2) NOT NULL,
    `horas_trabalhadas` DECIMAL(10,2) NOT NULL,
    `horas_extras` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`funcionario_comissionado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`funcionario_comissionado` (
    `id` INT NOT NULL,
    `salario_base` DECIMAL(10,2) NOT NULL,
    `comissao_percentual` DECIMAL(5, 2) NOT NULL,
    `vendas_realizadas` DECIMAL(10,2) NOT NULL,
    `bonus` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`beneficio_desconto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`beneficio_desconto` (
    `id` INT AUTO_INCREMENT NOT NULL,
    `funcionario_id` INT NOT NULL,
    `descricao` VARCHAR(255) NOT NULL,
    `valor` DECIMAL(10,2) NOT NULL,
    `tipo` ENUM('BENEFICIO', 'DESCONTO') NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`funcionario_id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`banco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`banco` (
    `id` INT AUTO_INCREMENT NOT NULL,
    `funcionario_id` INT NOT NULL,
    `nome_banco` VARCHAR(255) NOT NULL,
    `agencia` VARCHAR(20) NOT NULL,
    `numero_conta` VARCHAR(50) NOT NULL,
    `tipo_conta` ENUM('CORRENTE', 'POUPANCA') NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`funcionario_id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `folhapagamento`.`folha_pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `folhapagamento`.`folha_pagamento` (
    `id` INT AUTO_INCREMENT NOT NULL,
    `funcionario_id` INT NOT NULL,
    `data_geracao` DATE NOT NULL,
    `periodo_inicio` DATE NOT NULL,
    `periodo_fim` DATE NOT NULL,
    `valor_pago` DECIMAL(10,2) NOT NULL,
    `observacoes` TEXT NOT NULL,
    PRIMARY KEY (`id`),
	  FOREIGN KEY (`funcionario_id`)
	  REFERENCES `folhapagamento`.`funcionario` (`id`)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=0;