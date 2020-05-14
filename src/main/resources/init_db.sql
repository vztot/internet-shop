CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`(
    `product_id` BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(256) NOT NULL,
    `price`      DECIMAL(12,2)  NOT NULL,
    PRIMARY KEY (`product_id`));

CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `login` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

CREATE TABLE `internet_shop`.`roles` (
  `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC));

INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('ADMIN'), ('USER');

CREATE TABLE `internet_shop`.`users_roles` (
  `user_id` BIGINT(11) NOT NULL,
  `role_id` BIGINT(11) NOT NULL,
  INDEX `fk_users_roles_users_idx` (`user_id` ASC),
  INDEX `fk_users_roles_roles_idx` (`role_id` ASC),
  CONSTRAINT `fk_users_roles_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_roles_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `internet_shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orders_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_orders_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`orders_products` (
  `order_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `fk_orders_products_orders_idx` (`order_id` ASC),
  INDEX `fk_orders_products_products_idx` (`product_id` ASC),
  CONSTRAINT `fk_orders_products_orders`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_products_products`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`shopping_carts` (
  `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_shopping_carts_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`shopping_carts_products` (
  `cart_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `fk_shopping_carts_products_shopping_carts_idx` (`cart_id` ASC),
  INDEX `fk_shopping_carts_products_products_idx` (`product_id` ASC),
  CONSTRAINT `fk_shopping_carts_products_shopping_carts`
    FOREIGN KEY (`cart_id`)
    REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_carts_products_products`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
