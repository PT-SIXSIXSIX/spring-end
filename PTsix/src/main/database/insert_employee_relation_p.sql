drop PROCEDURE if exists insert_employee_relation_p;
delimiter $$
create PROCEDURE insert_employee_relation_p(in userId INTEGER,in staffId INTEGER)
begin
	declare storeId integer;
	select id into storeId  from ykat_stores where user_id = userId;
	
	insert into ykat_employee_relations(employee_id,store_id) values (staffId,storeId);
end $$
delimiter ;
