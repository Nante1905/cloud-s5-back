create or replace view v_annonce_non_valide as
(
    select 
    *
    from annonce 
    where status = 0
);