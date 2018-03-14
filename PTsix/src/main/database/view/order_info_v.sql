drop view if exists order_info_v;

create view order_info_v as
select o.id id,o.order_id,o.created_at order_created_at ,o.`status` order_status, o.type order_type, p.descp project_descp, p.price project_price,
p.type project_type, d.driver_name,d.driver_phone,s.company_name,s.user_id
from ykat_orders o, ykat_projects p, ykat_stores s, ykat_drivers d
where o.project_id = p.id and o.driver_id = d.id and o.store_id = s.id  ;