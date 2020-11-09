insert into werknemers(
                       familienaam,
                       voornaam,
                       chefid,
                       jobtitelid,
                       salaris,
                       geboorte,
                       email
                       )
VALUES(
       'achternaambaas',
       'voornaambaas',
       null,
       1,
       7500,
       '2000-12-31',
       concat(voornaam, '.', familienaam, '@toysforboys.com')
       );

