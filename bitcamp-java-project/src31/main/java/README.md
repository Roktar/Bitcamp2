# 변경 내역
- build.gradle 내용 추가
- eclipse 설정 파일 갱신
- pms_board 테이블 생성
```
CREATE TABLE pms_board(
    bno integer not null,
    titl varchar(255) not null,
    cont text,
    cdt datetime not null
);

alter table pms_board
    add constraint pms_board_pk primary key (bno);

alter table pms_board
    modify column bno int not null auto_increment;
```
- boardController 변경

- pms_member 테이블 생성
```
CREATE TABLE pms_member(
    mid varchar(20) not null,
    email varchar(255) not null,
    pwd varchar(100) not null
);

alter table pms_member
    add constraint pms_member_pk primary key (mid);
    
```

- member.java에서 생성자 제거

- pms_classroom 테이블 생성
```
CREATE TABLE pms_classroom(
    crno int not null,
    titl varchar(255) not null,
    sdt datetime not null,
    edt datetime not null,
    room varchar(50)
);

alter table pms_classroom 
    add constraint pms_classroom_pk primary key(crno);
    
alter table pms_classroom 
    modify column crno int not null auto_increment;

```

- pms_team테이블 생성
```
CREATE TABLE pms_team(
    name varchar(100) not null,
    dscrt text,
    max_qty int not null,
    sdt datetime not null,
    edt datetime not null
);

alter table pms_team
    add constraint pms_team_pk primary key(name);

```

- pms_task테이블 생성
```
CREATE TABLE pms_task(
    tano int not null,
    titl varchar(255) not null,
    sdt datetime not null,
    edt datetime not null,
    stat int default 0,
    mid varchar(20) not null,
    tnm varchar(100) not null
);

alter table pms_task
    add constraint pms_task_pk primary key(tano);

alter table pms_task
    modify column tano int not null auto_increment;

alter table pms_task
    add constraint pms_task_fk1 foreign key(mid) references pms_member(mid);

```

- pms_teammember테이블 생성
```
CREATE TABLE pms_team_member(
    tnm varchar(100) not null,
    mid varchar(20) not null
);

alter table pms_team_member
    add constraint pms_team_member_pk primary key(tnm, mid);

```
