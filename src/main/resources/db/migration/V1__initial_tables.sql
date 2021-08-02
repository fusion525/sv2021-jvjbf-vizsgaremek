CREATE TABLE courts (
    court_id bigint AUTO_INCREMENT,
    court_open date,
    court_close date

    primary key (court_id)
    );

CREATE TABLE customers (
    cust_id bigint AUTO_INCREMENT,
    cust_name varchar(200),
    cust_email varchar(200),
    cust_phone varchar(100)

    primary key (cust_id)
);

CREATE TABLE reservations (
    res_id bigint AUTO_INCREMENT,
    res_time date

    primary key (res_id)
);
