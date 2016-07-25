use NetEase
drop table #temp
select A.拨出用户,A.操作时间 as start,B.操作时间 as ending into #temp from phone_call_log A,phone_call_log B where A.拨出用户=B.拨出用户 and A.操作类型=1 and B.操作类型=2 and A.操作时间<=B.操作时间
declare @x bigint--总通话秒数
select @x=SUM(DATEDIFF(second,a.start,a.ending)) from #temp a where not exists(select * from #temp where a.拨出用户=拨出用户 and a.start=start and a.ending>ending)
declare @y bigint--总通话分钟数（不足一分钟按一分钟记）
select @y=SUM(ceiling(DATEDIFF(second,a.start,a.ending)/60.0)) 	from #temp a where not exists(select * from #temp where a.拨出用户=	拨出用户 and a.start=start and a.ending>ending)
select 1.5*@y-0.025*@x as "收入减少（毛）"
