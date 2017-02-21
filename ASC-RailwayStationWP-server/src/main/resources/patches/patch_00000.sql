CREATE TABLE sys_config (
id serial not null,
name character varying(20) not null,
val character varying(20),
CONSTRAINT sys_config_pkey PRIMARY KEY (id)
);


INSERT INTO sys_config (name, val) VALUES ('last_patch', '0');
