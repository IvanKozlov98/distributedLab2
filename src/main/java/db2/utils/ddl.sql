create table nodes (
  id bigint PRIMARY KEY,
  tag_id bigint REFERENCES tags,
  lat REAL,
  lon REAL,
  nameOfUser VARCHAR(100),
  uid bigint,
  visible Boolean,
  version bigint,
  changeset bigint
);

create table tags(
	id bigint primary key,
	k VarChar(1000),
	v VarChar(1000)
);


create table ways (
  id bigint PRIMARY KEY,
  tag_id bigint REFERENCES tags,
  nameOfUser VARCHAR(100),
  uid bigint,
  visible Boolean,
  version bigint,
  changeset bigint
);

ALTER TABLE tags ADD node_id bigint;
ALTER TABLE tags ADD FOREIGN KEY (node_id) REFERENCES nodes;

ALTER TABLE tags ADD way_id bigint;
ALTER TABLE tags ADD FOREIGN KEY (way_id) REFERENCES nodes;

select * from tags;
