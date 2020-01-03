create or replace procedure delete_board  --프로시저 이름 
(
--매개변수
    p_no  number,
    p_groupno number,
    p_step    number
)
is
--변수선언부
    cnt number;
begin
--처리할 내용
    --답변있는 원본글인 경우 delflag를 Y로 update, 나머지는 delete
    if p_step=0 then --원본글 (delfalg = Y)
        --답변이 있는지 체크
        select count(*) into cnt
        from reboard
        where groupno=p_groupno;
        
        if cnt>1 then --답변이 있는 경우
            update reboard
            set delflag='Y'
            where no=p_no;
        else    --답변이 없는 경우
            delete from reboard
            where no=p_no;
        end if;
  
    else    --답변글 (삭제)
        delete from reboard
        where no=p_no;
    end if;    
        
    commit;

EXCEPTION
    WHEN OTHERS THEN
	raise_application_error(-20001, '글 삭제 실패!');
        ROLLBACK;
end;
