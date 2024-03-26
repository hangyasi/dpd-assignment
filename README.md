# DPD Group Java/React feladat

A jelen repository tartalmazza a backend kódot, valamint egy docker-compose.yml-t a solution mappában, mely elindítja
az adatbázist, a backend valamint a frontend docker image-ét.

Az elindításhoz terminálból adjuk ki az alábbi parancsokat:

```bash
cd solution
docker-compose up
```

Ezt követően az applikáció elérhető localhost:3000-en

## Backend

A Backend Java 17 és Springboot 3 használatával készült. A backend elindítása előtt szükséges elindítani a postgres dockert az alábbiak szerint:

```bash
cd src/docker
docker-compose up
```

A fenti kód elindít egy üres alpine image-et előre telepített postgresql dbvel.

(Fontos, hogy eközben ne fusson postgres service az 5432-es porton)

Ezt követően indítható a backend applikáció, mely liquibase segítségével felépíti az adatbázist, majd ezt követően használható a három endpoint.

Példa request az adatbázis feltöltésére:

```bash
curl --location '127.0.0.1:8080/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Name",
"birthdate": "1990-01-01",
"birthPlace": "Springfield2",
"mothersName": "Jane Doe2",
"socialSecurityNumber": "123456789",
"taxIdentificationNumber": "987654321",
"emailAddress": "johndoe@example.com",
"addresses": [
{
"postalCode": "12346",
"country": "U4343SA",
"city": "Springfield",
"street": "Main St",
"number": "42"
},
{
"postalCode": "12347",
"country": "HU3434N",
"city": "Springfield",
"street": "Main St",
"number": "46"
}
],
"phoneNumbers": [
{
"phoneNumber": "55435-5432"
},
{
"phoneNumber": "77437-5432"
},
{
"phoneNumber": "11431-1111"
}
]
}'
```

## Frontend

A Frontend React Nextjs starter app segítségével készült.

A kódbázis az alábbi repositoryban található:

https://github.com/hangyasi/dpd-assignment-ui

A frontend indítása az alábbi parancsok kiadásával történhet:

```bash
npm install
npm run dev
```


Ezt követően a frontend a localhost:3000-en indul el.

Egy egyszerű listázó képernyő fogad minket, ahol lehetőség van a details action gomb megnyomásával az Address és Phone Number addatok kiírására (több szintű táblázat).
A sorban dupla kattintással a mezők szerkeszthetőkké válnak, melyet a Save (ceruza) action gombbal lehet menteni.
Ezen kívül lehetőségünk van egy Modal ablak megnyitására, ahol új Usert lehet a fromon elmenteni (minden mező kitöltendő, az alap validációk kiírásra kerülnek).