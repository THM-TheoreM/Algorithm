use NetEase
drop table #temp
select A.�����û�,A.����ʱ�� as start,B.����ʱ�� as ending into #temp from phone_call_log A,phone_call_log B where A.�����û�=B.�����û� and A.��������=1 and B.��������=2 and A.����ʱ��<=B.����ʱ��
declare @x bigint--��ͨ������
select @x=SUM(DATEDIFF(second,a.start,a.ending)) from #temp a where not exists(select * from #temp where a.�����û�=�����û� and a.start=start and a.ending>ending)
declare @y bigint--��ͨ��������������һ���Ӱ�һ���Ӽǣ�
select @y=SUM(ceiling(DATEDIFF(second,a.start,a.ending)/60.0)) 	from #temp a where not exists(select * from #temp where a.�����û�=	�����û� and a.start=start and a.ending>ending)
select 1.5*@y-0.025*@x as "������٣�ë��"
