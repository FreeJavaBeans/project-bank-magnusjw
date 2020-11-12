set schema 'bank';

create TABLE "Customer"
(
	"CustomerId" SERIAL not null primary key,
	"Username" VARCHAR(20) not null,
	"Password" VARCHAR(20) not null,
	"FirstName" VARCHAR(20) not null,
	"LastName" VARCHAR(20) not null
	
);

create TABLE "Employee"
(
	"EmployeeId" SERIAL not null primary key,
	"Username" VARCHAR(20) not null,
	"Password" VARCHAR(20) not null,
	"FirstName" VARCHAR(20) not null,
	"LastName" VARCHAR(20) not null
);

create TABLE "Account"
(
	"AccountId" SERIAL not null primary key,
	"CustomerId" INT not null,
	"Balance" NUMERIC(10,2) not null,
	"Status" VARCHAR(8) default 'PENDING',
	constraint "FK_Customer" foreign key ("CustomerId") references "Customer"("CustomerId") ON DELETE NO ACTION ON UPDATE NO ACTION
);

create table "Transaction"
(
	"TransactionId" SERIAL not null primary key,
	"RecipientId" INT not null, -- This is the Foreign Key
	"SenderId" int,
	"Type" int not null, -- 0=deposit, 1=withdraw, 2=transfer
	"Amount" NUMERIC(10,2) not null,
	"Status" VARCHAR(8) default 'PENDING',
	constraint "FK_Account" foreign key ("RecipientId") references "Account"("AccountId")ON DELETE NO ACTION ON UPDATE NO ACTION
);


select * from "Transaction" where "Status" = 'PENDING' and "RecipientId" in (select "AccountId" from "Account" where "CustomerId" = 1);

select "AccountId" from "Account" where "CustomerId" = 1;

insert into "Customer" ("Username", "Password", "FirstName", "LastName") values ('CoolGuy', 'Monster', 'John', 'Magnuson');
insert into "Customer" ("Username", "Password", "FirstName", "LastName") values ('CoolGirl', 'Cupcake', 'Taylor', 'Lee');

insert into "Employee" ("Username", "Password", "FirstName", "LastName") values ('EMP_alec', 'javabean', 'Alec', 'TallMan');

insert into "Account" ("CustomerId", "Balance") values (1, 100);

insert into "Account" ("CustomerId", "Balance") values (1, 200);
insert into "Account" ("CustomerId", "Balance") values (2, 600);

insert into "Account" ("CustomerId", "Balance") values (2, 400);
insert into "Account" ("CustomerId", "Balance") values (2, 800);

insert into "Transaction" ("RecipientId", "Type", "Amount", "Status") values (2, 0, 300, 'APPROVED');

insert into "Transaction" ("RecipientId", "SenderId", "Type", "Amount") values (2, 1, 2, 500);

update "Transaction" set "Status" = 'APPROVED' where "TransactionId" = 5;

insert into "Account" ("AccountId", "CustomerId", "Balance", "Status") values (5, 1, 700, 'PENDING');

select * from "Customer";
select * from "Employee";
select * from "Account" where "AccountId" = 2;
select * from "Transaction";

select * from "Account" where "AccountId" = 1;

select * from "Transaction" where "Status" = 'PENDING';

select * from "Account" where "CustomerId" in (select "CustomerId" from "Customer" where "CustomerId" = 1) order by "AccountId";

insert into "Transaction" ("RecipientId", "SenderId", "Type", "Amount") values (2, 3, 2, 300);





