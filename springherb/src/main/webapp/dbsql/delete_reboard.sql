create or replace procedure delete_board  --���ν��� �̸� 
(
--�Ű�����
    p_no  number,
    p_groupno number,
    p_step    number
)
is
--���������
    cnt number;
begin
--ó���� ����
    --�亯�ִ� �������� ��� delflag�� Y�� update, �������� delete
    if p_step=0 then --������ (delfalg = Y)
        --�亯�� �ִ��� üũ
        select count(*) into cnt
        from reboard
        where groupno=p_groupno;
        
        if cnt>1 then --�亯�� �ִ� ���
            update reboard
            set delflag='Y'
            where no=p_no;
        else    --�亯�� ���� ���
            delete from reboard
            where no=p_no;
        end if;
  
    else    --�亯�� (����)
        delete from reboard
        where no=p_no;
    end if;    
        
    commit;

EXCEPTION
    WHEN OTHERS THEN
	raise_application_error(-20001, '�� ���� ����!');
        ROLLBACK;
end;
