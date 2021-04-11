create table offer (
    id varchar(36) primary key,
    annual_percentage_rate decimal,
    monthly_payment_amount decimal(10, 2),
    lender_name varchar(30),
    loan_id varchar(36),
    payment_amount integer,
    paid_amount integer,
    created_at datetime,
    modified_at datetime,
    FOREIGN KEY (loan_id) references loan (id)
) engine = INNODB;
