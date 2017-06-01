CREATE TABLE audit_trial(
  id BIGINT NOT NULL AUTO_INCREMENT,
  account_id BIGINT,
  sale_id BIGINT,
  CONSTRAINT pk_audit_trial PRIMARY KEY(id)
);

CREATE TABLE sale (
  id BIGINT NOT NULL AUTO_INCREMENT,
  price INT NOT NULL,
  sell_date TIMESTAMP NOT NULL,

  ticket_account_id BIGINT NOT NULL,
  ticket_concert_id  BIGINT NOT NULL,
  audit_trial_id BIGINT NOT NULL,

  CONSTRAINT pk_sale PRIMARY KEY(id),
  CONSTRAINT fk_account_id FOREIGN KEY (ticket_account_id) REFERENCES ticket(account_id),
  CONSTRAINT ticket_concert_id FOREIGN KEY(ticket_concert_id) REFERENCES ticket(concert_id),
  CONSTRAINT fk_audit_trial_id FOREIGN KEY (audit_trial_id) REFERENCES audit_trial(id),
  CONSTRAINT chk_price CHECK (price > 0)
);