DROP TABLE IF EXISTS Product_category;
DROP TABLE IF EXISTS PRODUCTS;
DROP TABLE IF EXISTS Category;

Create Table Products(id INTEGER AUTO_INCREMENT PRIMARY KEY,name VARCHAR(250),description VARCHAR(250), quantity INTEGER, price DECIMAL);
create table Category(id INTEGER AUTO_INCREMENT PRIMARY KEY,name VARCHAR(250), description VARCHAR(250));
create table Product_category(product_id INTEGER,category_id INTEGER);


insert into Products(name,description,quantity,price) values('IKIGAI','Novel based on Japaneese Culture',5, 310.5);
insert into Products(name,description,quantity,price) values('Monk Who Sold His Ferrari','Movtivational Novel',5, 120);
insert into Products(name,description,quantity,price) values('Tuesday with Morrie','Novel based on Life Experiences',3, 120);
insert into Products(name,description,quantity,price) values('One Plus Seris 5G','ULTRA FAST SNAPDRAGOn 865',3,40000);
insert into Products(name,description,quantity,price) values('iPhone11','12MP ultra wide Camera',3,66990);
insert into Category(name,description) values('Novels','Novels and Books');
insert into Category(name,description) values('Mobiles,Computers','Mobiles,Power Banks,Tablets,PenDrives');
insert into Category(name,description) values('Sports,Fitness,Bags,Luggage','All Sports Fitness related Stuff');

insert into Product_Category(product_id,category_id) values(1,1);
insert into Product_Category(product_id,category_id) values(2,1);
insert into Product_Category(product_id,category_id) values(3,2);
insert into Product_Category(product_id,category_id) values(4,2);