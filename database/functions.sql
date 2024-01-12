CREATE OR REPLACE FUNCTION topSellers(dateYYYYMM character varying, limit_value integer)
RETURNS TABLE (
    id integer,
    nom character varying,
    valide bigint,
    vendu bigint,
    commission numeric,
    pourcentage numeric
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        utilisateur.id,
        utilisateur.nom,
        COALESCE(COUNT(v_annonce_valide.id), 0) AS valide,
        COALESCE(COUNT(v_annonce_vendu.id), 0) AS vendu,
        COALESCE(SUM(v_annonce_valide.commission), 0) AS commission,
        CASE
            WHEN COUNT(v_annonce_valide.id) = 0 THEN 0.0
            ELSE (COUNT(v_annonce_vendu.id) / COUNT(v_annonce_valide.id)) * 100
        END AS pourcentage
    FROM
        utilisateur
    LEFT JOIN v_annonce_valide ON v_annonce_valide.id_utilisateur = utilisateur.id AND TO_CHAR(v_annonce_valide.date_maj::date, 'YYYYMM') <= dateYYYYMM
    LEFT JOIN v_annonce_vendu ON v_annonce_vendu.id_utilisateur = utilisateur.id AND TO_CHAR(v_annonce_vendu.date_maj::date, 'YYYYMM') <= dateYYYYMM
    GROUP BY
        utilisateur.id,
        utilisateur.nom
    ORDER BY
        valide desc
    LIMIT
        limit_value;

    RETURN;
END;
$$ LANGUAGE plpgsql;
select * from  topSellers('202401',2);