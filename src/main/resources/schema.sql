DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES (
    brand_id INT NOT NULL,
    start_date VARCHAR(19) NOT NULL,
    end_date VARCHAR(19) NOT NULL,
    price_list INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL
);