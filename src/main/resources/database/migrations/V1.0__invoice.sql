create table invoice
(
  id          int(11) primary key,
  client      varchar(255),
  vatRate     int(11),
  invoiceDate datetime
)