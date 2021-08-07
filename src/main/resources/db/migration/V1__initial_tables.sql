CREATE TABLE `courts` (
    `court_id` BIGINT NOT NULL AUTO_INCREMENT,
    `court_name` VARCHAR(200),
    `court_open` TIME,
    `court_close` TIME,
    PRIMARY KEY (`court_id`)
);

CREATE TABLE `customers` (
    `cust_id` BIGINT NOT NULL AUTO_INCREMENT,
    `cust_name` VARCHAR(200),
    `cust_email` VARCHAR(200),
    `cust_phone` VARCHAR(100),
     PRIMARY KEY (`cust_id`)
);

CREATE TABLE `reservations` (
    `res_id` BIGINT NOT NULL AUTO_INCREMENT,
    `res_time` DATE,
    PRIMARY KEY (`res_id`)
);
