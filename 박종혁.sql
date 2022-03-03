DROP TABLE trade_user;

CREATE TABLE trade_user(
	uno INT PRIMARY KEY auto_increment,
    u_id VARCHAR(50) NOT NULL,
    u_pw VARCHAR(200) NOT NULL,
    u_name VARCHAR(100) NOT NULL,
    u_birth VARCHAR(100) NOT NULL,
    u_addr_post VARCHAR(100) NOT NULL,
    u_addr VARCHAR(50) NOT NULL,
    u_addr_detail VARCHAR(50) NOT NULL,
    u_phone VARCHAR(100) NOT NULL,
    u_visit_date TIMESTAMP NOT NULL DEFAULT now(),
    u_withdraw char(1) DEFAULT 'n'
);

CREATE TABLE user_auth(
	uno INT,
	u_id VARCHAR(50) NOT NULL,
	u_auth VARCHAR(100) NOT NULL,
	CONSTRAINT fk_uno FOREIGN KEY(uno) REFERENCES trade_user(uno)
);

CREATE TABLE IF NOT EXISTS board(
	bno INT PRIMARY KEY AUTO_INCREMENT,	-- 번호
    title VARCHAR(50) NOT NULL,			-- 제목
    content VARCHAR(50) NOT NULL,		-- 내용
    writer VARCHAR(50) NOT NULL,		-- 작성자
    regdate TIMESTAMP DEFAULT NOW(),	-- 등록일자
    updateDate TIMESTAMP DEFAULT NOW(),	-- 수정일자
    fileName varchar(500) default null, -- 파일이름
	filePath varchar(500) default null 	-- 파일경로
);

insert into board(title,content,writer) values('테스트 제목1','테스트 내용1','최기근');

CREATE TABLE IF NOT EXISTS wish(
	bno INT,
    uno INT,
    CONSTRAINT fk_bno FOREIGN KEY(bno) REFERENCES board(bno)
);

insert into wish values(1,1);
insert into wish values(3,1);
insert into wish values(1,3);

commit;