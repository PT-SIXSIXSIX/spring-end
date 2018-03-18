drop view if exists article_info_v;

create view article_info_v as
select a.article_id, a.title, a.summary, a.content,a.type article_type,a.banner_url, a.updated_at,a.author, a.status article_status,
s.user_id 
from ykat_articles a, ykat_stores s
where a.store_id = s.id;