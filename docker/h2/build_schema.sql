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

DROP TABLE IF EXISTS schedules;

CREATE TABLE schedules (
	id UUID NOT NULL,
	destination VARCHAR(255) NOT NULL,
	source VARCHAR(255) NOT NULL,
	amount NUMERIC(20, 2) NOT NULL,
	transfer_date DATE NOT NULL,
	tax NUMERIC(20, 2) NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	deleted_at TIMESTAMP,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,

	PRIMARY KEY (id)
);
