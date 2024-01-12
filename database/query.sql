-- benefice par mois
select coalesce(sum(commission), 0) commission from annonce_vendu av join annonce a on av.id_annonce = a.id where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019;

-- date vendu vs date annonce
select avg(a.date_creation - av.date_vente)
from annonce_vendu av
join annonce a 
on av.id_annonce = a.id
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019

-- nb annonce par mois
select count(*) from annonce 
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019


--  nb vendu par mois
select count(*) from annonce_vendu 
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019

-- vente par mois
create view v_mois as select generate_series(1,12) mois;
create view v_vente as select a.*, av.date_vente 
from annonce_vendu av 
join annonce a on av.id_annonce = a.id;

select m.mois, coalesce(sum(v.commission), 0) commission
from v_mois m
left outer join 
    (select * 
    from v_vente 
    where extract(month from date_vente) = 2019 and extract(year from date_vente) = 2019) v 
on m.mois = extract(month from v.date_vente)
group by m.mois
order by m.mois;











create or replace view v_top_seller as
(select 
utilisateur.id, utilisateur.nom, coalesce(count(v_annonce_valide.id),0) as valide, coalesce(count(v_annonce_vendu.id),0) as vendu, coalesce(sum(v_annonce_valide.commission),0) as commission,
case when 
count(v_annonce_valide.id) = 0 then 0
else 
    (count(v_annonce_vendu.id)/count(v_annonce_valide.id))*100
end as pourcentage 
from 
utilisateur
left join v_annonce_valide
    on v_annonce_valide.id_utilisateur = utilisateur.id
    and TO_CHAR(v_annonce_valide.date_maj, 'YYYYMM') <= '202402'
left join v_annonce_vendu
    on v_annonce_vendu.id_utilisateur = utilisateur.id
    and TO_CHAR(v_annonce_vendu.date_maj, 'YYYYMM') <= '202402'
group by utilisateur.id, utilisateur.nom 
order by valide limit 1
);

insert into historique_annonce values 
(default, '2024-01-01', 5,1),
(default, '2023-12-22', 5,2),
(default, '2023-12-23', 5,10),
(default, '2023-12-15', 5,12),
(default, '2023-12-14', 5,13);

insert into historique_annonce values 
(default, '2024-01-01', 10,1);