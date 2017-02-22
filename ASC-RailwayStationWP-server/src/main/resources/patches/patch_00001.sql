CREATE TABLE users
(
  id bigint NOT NULL,
  address character varying(255),
  country character varying(255),
  email character varying(255),
  last_name character varying(255),
  "login" character varying(255),
  middle_name character varying(255),
  "name" character varying(255),
  "password" character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;


CREATE TABLE trains
(
  id bigint NOT NULL,
  "number" integer,
  CONSTRAINT trains_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE trains OWNER TO postgres;


CREATE TABLE stations
(
  id bigint NOT NULL,
  "name" character varying(255),
  CONSTRAINT stations_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE stations OWNER TO postgres;



CREATE TABLE carriages
(
  id bigint NOT NULL,
  "carriageType" character varying(255),
  "number" integer,
  train_id bigint,
  CONSTRAINT carriages_pkey PRIMARY KEY (id),
  CONSTRAINT fk_29uilufskcaew3ny1bxnggp8a FOREIGN KEY (train_id)
      REFERENCES trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE carriages OWNER TO postgres;



CREATE TABLE places
(
  id bigint NOT NULL,
  "number" integer,
  carriage_id bigint,
  train_id bigint,
  CONSTRAINT places_pkey PRIMARY KEY (id),
  CONSTRAINT fk_1c1yrw9f04ox4ivsjbixwog9a FOREIGN KEY (carriage_id)
      REFERENCES carriages (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_o3q2h7hgk1uka0pg9pxd8642 FOREIGN KEY (train_id)
      REFERENCES trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE places OWNER TO postgres;



CREATE TABLE cabinets
(
  id bigint NOT NULL,
  user_id bigint,
  CONSTRAINT cabinets_pkey PRIMARY KEY (id),
  CONSTRAINT fk_serp7fq7hsa2ix258ipuwfqd7 FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cabinets OWNER TO postgres;


CREATE TABLE orders
(
  id bigint NOT NULL,
  price double precision,
  "time" timestamp without time zone,
  cabinet_id bigint,
  CONSTRAINT orders_pkey PRIMARY KEY (id),
  CONSTRAINT fk_8aucpin6r3qr9eg8ot485k1vh FOREIGN KEY (cabinet_id)
      REFERENCES cabinets (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE orders OWNER TO postgres;



CREATE TABLE train_schedules
(
  id bigint NOT NULL,
  arrival_date timestamp without time zone,
  departure_date timestamp without time zone,
  order_id bigint,
  station_id bigint,
  train_id bigint,
  CONSTRAINT train_schedules_pkey PRIMARY KEY (id),
  CONSTRAINT fk_4uuehmcl1y1nj4apb9kwujsg6 FOREIGN KEY (train_id)
      REFERENCES trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_8m8is4p69pp9um7q8prnhnrhf FOREIGN KEY (order_id)
      REFERENCES orders (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lvc5iydkuyav8mg48ikvflmw7 FOREIGN KEY (station_id)
      REFERENCES stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE train_schedules OWNER TO postgres;

CREATE TABLE passengers
(
  id bigint NOT NULL,
  "name" character varying(255),
  rate double precision,
  order_id bigint,
  place_id bigint,
  last_name bigint,
  CONSTRAINT passengers_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ln6bnsev3q4785j4kyk21j3uo FOREIGN KEY (last_name)
      REFERENCES train_schedules (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_n8jlwobc8ys5av70r1k3tossl FOREIGN KEY (place_id)
      REFERENCES places (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_opv4b211cumpqxomrp38dhis7 FOREIGN KEY (order_id)
      REFERENCES orders (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE passengers OWNER TO postgres;


