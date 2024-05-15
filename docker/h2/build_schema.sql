DROP TABLE IF EXISTS transfer_taxes;

CREATE TABLE transfer_taxes (
	days_period INT NOT NULL,
	tax NUMERIC(20, 2) NOT NULL,
       PRIMARY KEY (days_period)
);

INSERT INTO transfer_taxes (days_period, tax) VALUES (0, 2.5);
INSERT INTO transfer_taxes (days_period, tax) VALUES (10, 0);
INSERT INTO transfer_taxes (days_period, tax) VALUES (20, 8.2);
INSERT INTO transfer_taxes (days_period, tax) VALUES (30, 6.9);
INSERT INTO transfer_taxes (days_period, tax) VALUES (40, 4.7);
INSERT INTO transfer_taxes (days_period, tax) VALUES (50, 1.7);