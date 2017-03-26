ALTER TABLE train_schedules DROP COLUMN order_id;

DROP TABLE orders CASCADE;

DROP TABLE passengers CASCADE;
DROP TABLE cabinets CASCADE;

CREATE TABLE tickets
(
  id bigint NOT NULL,
  departure_station bigint,
  departure_date timestamp without time zone NOT NULL,
  arrival_station bigint,
  arrival_date timestamp without time zone NOT NULL,
  train_id bigint NOT NULL,
  carriage_id bigint NOT NULL,
  place_id bigint NOT NULL,
  user_id bigint,
  CONSTRAINT tickets_pkey PRIMARY KEY (id),
  CONSTRAINT fk_trains FOREIGN KEY (train_id)
      REFERENCES trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_carriages FOREIGN KEY (carriage_id)
      REFERENCES carriages (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_places FOREIGN KEY (place_id)
      REFERENCES carriages (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_users FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_departure_station FOREIGN KEY (departure_station)
      REFERENCES stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_arrival_station FOREIGN KEY (arrival_station)
      REFERENCES stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
