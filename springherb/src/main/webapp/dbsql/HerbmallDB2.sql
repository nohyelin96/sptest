--[3] 장바구니
--drop table Cart cascade constraint;
CREATE TABLE Cart
(
  cartNo	number	Primary Key NOT NULL, 	--일련번호
  customerId	VarChar2(20) 
	  references member2(userid) on delete cascade not null,	 --고객구분번호 : 고유접속번호(세션ID)
  productNo	number 
	  references products(productNo) NOT NULL,		--상품고유번호(Product테이블의 ProductNo필드)
  quantity		number NOT NULL,		--수량
  regdate	 	Date    Default sysdate		--생성일
);

--drop sequence cart_seq;
create sequence cart_seq
start with 1
increment by 1
nocache;

Select * From Cart;


--[4] 주문
--drop table orders cascade constraint;
CREATE TABLE Orders
(
  orderNo		number	NOT NULL Primary Key,   --주문번호
  customerId	varchar2(20) 
	references member2(userid) NOT NULL ,		--고객고유번호(회원아이디)(FK)
  totalPrice		number Null,			--주문총금액
  deliveryStatus	VarChar2(20) default '입금확인중',		--주문상황_배송상황(1:입금확인중, 2:결제완료, 3:배송중, (배송준비중) 4:배송완료)
  orderDate		Date default sysdate ,		--주문일자
  deliveryDate		Date  NULL,		--배송일자
  paymentEndDate	Date Null,				--결제완료일
  message			varchar2(150),			--남기고싶은말
  -- pwd		VarChar2(20) Null		--주문비밀번호_비회원

  customerName	VarChar2(50),		--받는사람이름
  --tel			VarChar2(20) Null,	--전화번호
  hp				VarChar2(20) Null,	--휴대폰번호
  zipcode			VarChar2(7) Null,	--우편번호
  address			VarChar2(100) Null,	--주소
  addressDetail		VarChar2(50) Null	--상세주소
);

--drop sequence orders_seq;
create sequence orders_seq
start with 1
increment by 1
nocache;


Select * From Orders;


--[5] 주문상세
--drop table OrderDetails cascade constraint;
CREATE TABLE OrderDetails
(
	orderNo	number 
		references orders(orderNo) on delete cascade NOT NULL ,	--주문번호(고유일련번호)
	productNo	number 
		references products(productNo) NOT NULL,	--상품번호
	quantity	number NOT NULL,	--주문수량
	constraint orderdetails_pk primary key(orderNo, productNo)
);


Select * From OrderDetails;

--[6] 배송지 정보
--drop table Delivery cascade constraint;
/*CREATE TABLE Delivery
(
  orderNo		number  primary key 
	references orders(orderNo) on delete cascade Not Null,	--주문번호
  customerName	VarChar2(50),		--받는 사람 이름
  --tel			VarChar2(20) Null,	--전화번호
  hp				VarChar2(20) Null,	--휴대폰번호
  zipcode			VarChar2(7) Null,	--우편번호
  address			VarChar2(100) Null,	--주소
  addressDetail		VarChar2(50) Null	--상세주소
);
*/
--------------------------------------------------------------------------------
select  c.cartno, c.quantity, c.productno,
p.imageurl, p.productname, p.sellprice
from cart c,  products  p
where C.PRODUCTNO=p.productno
and c.customerid='hong';

--view 만들기
--cart, products 테이블을 조인해서 만든 뷰
--장바구니 목록에서 사용
create or replace view CartView
as
select  c.cartno, c.quantity, c.productno,
p.imageurl, p.productname, p.sellprice, c.customerid
from cart c,  products  p
where C.PRODUCTNO=p.productno;
--------------------------------

select * from cartView
where customerid='hong';

---------------------------------------------
create or replace view orderDetailsView
as
select o.orderNo, o.productNo, o.quantity,
p.productName, p.sellPrice, p.imageUrl 
from orderDetails o, products p
where o.productNo = p.productNo;
-----------------------------------------------
select * from orderDetailsView
where orderNo=4;

--------------------------------------
create or replace view ordersView
as
select o.orderNo, o.customerId, o.totalPrice, 
o.deliveryStatus, o.orderDate, o.deliveryDate,
o.paymentEndDate,o.message, o.customerName,
o.hp, o.zipcode, o.address, o.addressDetail,
m.name 
from orders o, member2 m
where o.customerId=m.userid;
---------------------------------------
select * from ordersView
where orderNo=4;

-----------------------------------
Select * From products;
Select * From cart;
Select * From orders;
Select * From orderDetails;

Select * From member2;

Select * From products;
Select * From eventProduct;


