CREATE TABLE IF NOT EXISTS PRICES (
    brand_id INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    start_date VARCHAR(19) NOT NULL,
    end_date VARCHAR(19) NOT NULL
);

INSERT INTO PRICES (brand_id, product_id, priority, price, currency, start_date, end_date) VALUES
(1, 35455, 0, 35.50, 'EUR', '2020-06-14-00.00.00', '2020-12-31-23.59.59'),
(1, 35455, 1, 25.45, 'EUR', '2020-06-14-15.00.00', '2020-06-14-18.30.00'),
(1, 35455, 1, 30.50, 'EUR', '2020-06-15-00.00.00', '2020-06-15-11.00.00'),
(1, 35455, 1, 38.95, 'EUR', '2020-06-16-00.00.00', '2020-12-31-23.59.59');
