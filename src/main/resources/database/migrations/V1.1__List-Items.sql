create table listItem
(
  id          int primary key,
  quantity    int,
  description varchar(255),
  unitPrice   decimal(10, 2),
  invoice     int,
  foreign key (invoice) references invoice (id)
)