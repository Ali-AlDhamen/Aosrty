CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_email VARCHAR(255) NOT NULL Unique,
    customer_password VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_total_spent INT DEFAULT 0,
    customer_orders_number INT DEFAULT 0
);

CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_image LONGBLOB NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    product_store_id INT NOT NULL,
    product_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_store_id) REFERENCES store(store_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE partner (
    partner_id INT PRIMARY KEY AUTO_INCREMENT,
    partner_name VARCHAR(255) NOT NULL ,
    partner_email VARCHAR(255) NOT NULL Unique,
    partner_password VARCHAR(255) NOT NULL
);

CREATE TABLE store (
    store_id INT PRIMARY KEY AUTO_INCREMENT,
    store_image LONGBLOB NOT NULL,
    store_name VARCHAR(255) NOT NULL Unique,
    store_address VARCHAR(255) NOT NULL,
    store_phonenumber VARCHAR(255) NOT NULL,
    store_orders_number INT DEFAULT 0,
    store_owner_id INT,
    FOREIGN KEY (store_owner_id) REFERENCES partner(partner_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_time TIMESTAMP NOT NULL default now(),
    order_status VARCHAR(255) NOT NULL,
    order_customer_id INT,
    order_store_id INT,
    order_cost DECIMAL(10,2) NOT NULL,
    order_address varchar(255),
    FOREIGN KEY (order_customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (order_store_id) REFERENCES store(store_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orderitem (
    orderitem_id INT PRIMARY KEY auto_increment,
    orderitem_quantity INT NOT NULL,
    orderitem_product_id INT,
    orderitem_order_id INT,
    FOREIGN KEY (orderitem_product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (orderitem_order_id) REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE report (
report_id INT PRIMARY KEY auto_increment,
report_content TEXT NOT NULL,
customer_id INT,
report_datetime TIMESTAMP NOT NULL,
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE code (
  code_id INT AUTO_INCREMENT PRIMARY KEY,
  code_name VARCHAR(255) NOT NULL,
  code_value DECIMAL(10,2) NOT NULL,
  code_store_id Int,
  FOREIGN KEY (code_store_id) REFERENCES store(store_id) ON UPDATE CASCADE ON DELETE CASCADE
);



SELECT customer_name
FROM customer
WHERE customer_total_spent >= 500
UNION
SELECT customer_name
FROM customer
WHERE customer_orders_number >= 5;

SELECT customer_name
FROM customer
WHERE customer_total_spent >= 500
AND customer_orders_number >= 2;

SELECT customer_name
FROM customer
WHERE customer_total_spent >= 500
AND customer_name NOT IN (
  SELECT customer_name
  FROM customer
  WHERE customer_orders_number >= 2
);


SELECT DISTINCT partner_name
FROM partner;

SELECT sum(order_cost) as total_revenue
FROM orders where order_store_id = 1;


SELECT customer_name
FROM customer
WHERE customer_total_spent >= (SELECT AVG(customer_total_spent)
                                FROM customer);

SELECT product_name
FROM product
WHERE product_price > ANY (SELECT product_price
                           FROM product
                           WHERE product_name = 'iPhone 14');

SELECT store_name, SUM(product_price) as total_revenue
FROM store
JOIN product
ON store.store_id = product.product_store_id
GROUP BY store_name;

SELECT store_name, SUM(product_price) as total_revenue
FROM store
JOIN product
ON store.store_id = product.product_store_id
GROUP BY store_name
HAVING SUM(product_price) >= 1000;

SELECT customer_name, SUM(order_cost) as total_spent
FROM customer
JOIN orders
ON customer.customer_id = orders.order_customer_id
GROUP BY customer_name
ORDER BY total_spent DESC;

SELECT customer_name
FROM customer
WHERE customer_total_spent BETWEEN 1000 and 20000;


CREATE PROCEDURE insert_customer (IN customer_email VARCHAR(255),
                                   IN customer_password VARCHAR(255),
                                   IN customer_name VARCHAR(255))
BEGIN
  INSERT INTO customer (customer_email, customer_password, customer_name)
  VALUES (customer_email, customer_password, customer_name);
END;


CREATE PROCEDURE update_order_status (IN order_id INT,
                                      IN order_status VARCHAR(255))
BEGIN
  UPDATE orders SET order_status = order_status
  WHERE order_id = order_id;
END;

CREATE PROCEDURE get_total_amount_spent (IN customer_id INT,
                                         OUT total_spent DECIMAL(10, 2))
BEGIN
  SELECT SUM(order_cost) INTO total_spent
  FROM orders
  WHERE order_customer_id = customer_id;
END;

CREATE PROCEDURE get_store_product_details (IN store_id INT)
BEGIN
  SELECT product_id, product_name, product_price
  FROM product
  WHERE product_store_id = store_id;
END;

CREATE TRIGGER update_store_orders_number
AFTER INSERT ON orders
FOR EACH ROW
UPDATE store SET store_orders_number = store_orders_number + 1
WHERE store.store_id = NEW.order_store_id;



CREATE TRIGGER update_customer_info
AFTER INSERT ON orders
FOR EACH ROW
UPDATE customer SET customer_total_spent = customer_total_spent + NEW.order_cost,
 customer_orders_number = customer_orders_number + 1 WHERE customer_id = NEW.order_customer_id;


CREATE VIEW customer_orders AS
SELECT customer.customer_name, orders.order_time, orders.order_status, orderitem.orderitem_quantity
FROM customer
JOIN orders ON customer.customer_id = orders.order_customer_id
JOIN orderitem ON orders.order_id = orderitem.orderitem_id;



CREATE VIEW store_details AS
SELECT store.store_name, store.store_address, store.store_phonenumber, partner.partner_name
FROM store
JOIN partner ON store.store_owner_id = partner.partner_id;
