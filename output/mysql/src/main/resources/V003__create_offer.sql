create table offer (
    uuid uuid primary key,
    annual_percentage_rate decimal,
    monthly_payment_amount decimal(10, 2),
    payment_amount integer
) engine  = INNODB;
