--유저 테이블 생성
create table users(
    no	number,--번호
    id VARCHAR2(20) NOT NULL UNIQUE, --아이디
    password   	varchar2(20) NOT NULL, -- 비밀번호
    name	varchar2(20), -- 이름 
    gender VARCHAR2(10), -- 성별
    PRIMARY KEY(no)
);
--유저 시퀀스 생성
CREATE SEQUENCE seq_users_no
INCREMENT BY 1 
START WITH 1--숫자를 몇개를 잡아놓고 시작을 하기에 껏다키면 다른숫자부터 시작할때가 있음
NOCACHE;--그래서 이걸 사용하면 그런데 없음

--테이블 삭제
drop TABLE users;
--시퀀스 삭제
drop SEQUENCE seq_users_no;
--테이블 내용 삭제
DELETE FROM users;

--insert
INSERT INTO users 
VALUES (seq_users_no.nextval,
        'jwjw',
        '1234',
        '김우진',
        'man');
COMMIT;

SELECT
    *
    
FROM users;