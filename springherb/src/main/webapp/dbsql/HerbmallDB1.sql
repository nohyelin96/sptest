--[1] 카테고리
--drop table Category cascade constraint;

CREATE TABLE Category
(
    categoryNo		number	NOT NULL Primary Key, --카테고리 일련번호
    categoryName		VarChar2(50),                 --카테고리명
    categoryOrder		number	Default 0             --카테고리보여지는순서
);

--drop sequence category_seq;
create sequence category_seq
increment by 1
start with 1
nocache;

Insert Into Category Values(category_seq.nextval, '허브차', 0);
Insert Into Category Values(category_seq.nextval,'아로마오일', 1);
Insert Into Category Values(category_seq.nextval,'허브향초', 2);


/*Insert Into Category Values(1, '허브차', 0);
Insert Into Category Values(2,'아로마오일', 1);
Insert Into Category Values(3,'허브향초', 2);
*/

Select * From Category Order By CategoryNo Desc;


--[2] 상품
--drop TABLE Products cascade constraint;
CREATE TABLE Products
(
  productNo	number	NOT NULL	Primary Key,	--상품번호
  categoryNo	number	references category(categoryNo) NOT NULL,	--카테고리번호
  productName	VarChar2(50),                           --상품명
  sellPrice		number Null,                            --판매가
  company		VarChar2(50),                           --제조회사
  --eventName	VarChar2(50) default 'GEN'  Null,
	--신상품(NEW), 베스트(BEST), MD추천(MD), 일반(GEN), 진열안함(NONE)
  imageURL		VarChar2(50),                           --상품이미지
  explains		VarChar2(400),                          --요약설명
  description	varchar2(4000) Null,                    --상세설명
  regDate		Date Default sysdate,			--상품등록일
  mileage		number Default 0                            --마일리지(적립금)
);

--drop sequence products_seq;
create sequence products_seq
increment by 1
start with 1
nocache;


insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval, 1, '라벤다',  7000, '허브나라', 'lavender.jpg','원산지 : 독일
성분 : 라벤다 99%, 로즈마리
', '독특한 향기와 맛 때문에 고대로마시대부터 약용 및 조미식물로 널리 이용되어옴 고대 로마인들은 라벤더 잎이나 꽃을 목욕물에 섞어서 주로 이용 고기요리, 찌개, 양고기요리, 소스 등에 넣어 먹기도 하며 라벤더 오일은 화장품, 향류, 약용으로도 이용된다.
', 70);

insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval,1, '로즈마리',  8000, '허브나라', 'rosemary.jpg','원산지 : 독일
성분 : 로즈마리99%, 캐모마일', '라틴어의 바다의 이슬이라는 뜻으로, 고대부터 유대인,그리스인,이집트인,로마인에게 성스러운 허브로 간주 되어 왔으며 고기 요리에 자주 쓰인다.
', 80);

insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval,1, '쟈스민', 9000, '허브나라', 'jasmine.jpg','원산지 : 독일
성분 : 쟈스민 99%, 캐모마일',
'자스민은 감미롭고 이국적인 향을 지닌 허브로 꽃말로는 "사랑스러움"이란 뜻을 가 지고 있으며, 여성적 감성과 낭만의 상징으로 불리워왔다. 옛날부터 중국에서는 차 로 가장 이용되어 왔으며, 꽃을 건조하여 차로 마신다.
',90);


insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval,1, '라벤다허브차',  7000, '허브나라', 'lavenderherb.jpg','원산지 : 독일
성분 : 라벤다 99%, 로즈마리
', '독특한 향기와 맛 때문에 고대로마시대부터 약용 및 조미식물로 널리 이용되어옴 고대 로마인들은 라벤더 잎이나 꽃을 목욕물에 섞어서 주로 이용 고기요리, 찌개, 양고기요리, 소스 등에 넣어 먹기도 하며 라벤더 오일은 화장품, 향류, 약용으로도 이용된다.
', 70);

insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval,1, '로즈마리허브차',  8000, '허브나라', 'rosemaryherb.jpg','원산지 : 독일
성분 : 로즈마리99%, 캐모마일', '라틴어의 바다의 이슬이라는 뜻으로, 고대부터 유대인,그리스인,이집트인,로마인에게 성스러운 허브로 간주 되어 왔으며 고기 요리에 자주 쓰인다.
', 80);

insert into products(ProductNo, categoryNo, productname, sellprice, company,
imageurl, explains, description, Mileage)
values(products_seq.nextval,1, '쟈스민허브차',  9000, '허브나라', 'jasmineherb.jpg','원산지 : 독일
성분 : 쟈스민 99%, 캐모마일',
'자스민은 감미롭고 이국적인 향을 지닌 허브로 꽃말로는 "사랑스러움"이란 뜻을 가 지고 있으며, 여성적 감성과 낭만의 상징으로 불리워왔다. 옛날부터 중국에서는 차 로 가장 이용되어 왔으며, 꽃을 건조하여 차로 마신다.
',90);


--[3] 이벤트 상품 목록 테이블
--drop table eventproduct cascade constraint;
create table eventproduct(
  eventProductNo	number primary key, --기본키, 일련번호
  productNo	number references products(productNo) on delete cascade not null, --상품번호
  eventName	VarChar2(30) --NEW, BEST, MD
);

--drop sequence eventproduct_seq;
create sequence eventproduct_seq
increment by 1
start with 1
nocache;
 
insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 2, 'NEW');
insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 3, 'NEW');
insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 4, 'NEW');
insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 5, 'NEW');
insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 6, 'NEW');

 
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 1, 'BEST');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 2, 'BEST');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 3, 'BEST');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 4, 'BEST');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 5, 'BEST');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 6, 'BEST');


 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 3, 'MD');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 4, 'MD');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 5, 'MD');
 insert into eventproduct(eventProductNo, productNo, eventName)
values(eventproduct_seq.nextval, 6, 'MD');


commit;

Select * From Products;
Select * From eventproduct;
Select * From Category;

select * from products p, eventproduct e
where p.productNo = e.productNo
and e.eventName = 'MD';

--------------------------------
--뷰 생성
create or replace view productEventView
  as
  select p.*, e.eventName
     from products p, eventProduct e
     where p.productno=e.productno;
     
  select * from productEventView
  where eventName='NEW';
