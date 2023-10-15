ALTER TABLE samuraitravel_db.contact ADD COLUMN owner_id INTEGER;
UPDATE samuraitravel_db.contact SET owner_id = 1 WHERE id = 1;

ALTER TABLE samuraitravel_db.contactuser ADD COLUMN role_id INTEGER;
ALTER TABLE samuraitravel_db.roles ADD COLUMN role_id INTEGER;

