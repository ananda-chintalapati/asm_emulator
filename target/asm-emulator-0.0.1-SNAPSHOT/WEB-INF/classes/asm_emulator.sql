CREATE DATABASE asm_emulator IF NOT EXISTS;

CREATE TABLE pdu_request(
	pdu_number VARCHAR(50) NOT NULL,
	req_number VARCHAR(50) NOT NULL PRIMARY KEY,
	pdu_location VARCHAR(50) NOT NULL
);

CREATE TABLE device(
	id INT NOT NULL PRIMARY KEY,
	mfr_name VARCHAR(50),
	device_type VARCHAR(50),
	model_number VARCHAR(50),
	serial_number VARCHAR(50),
	mac_address VARCHAR(50),
	ip_address VARCHAR(50),
	domain VARCHAR(50),
	status VARCHAR(50)
);