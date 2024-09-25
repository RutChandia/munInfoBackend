Este proyecto realiza scrapping de información de comunas desde el Sistema Nacional de Información Municipal, usando [Ktor](https://ktor.io/) y [Jsoup](https://jsoup.org/).

## Endpoints

### 1. Listado de Municipalidades

- **Método:** `GET`
- **URL:** `/`
- **Descripción:** Este endpoint devuelve una lista de municipalidades con sus respectivos IDs.

#### Ejemplo de Respuesta:

```
[{
"id": "05602",
"name": "ALGARROBO"
},
{
"id": "13502",
"name": "ALHUÉ"
},
{
"id": "08314",
"name": "ALTO BIOBÍO"
},
{
"id": "03302",
"name": "ALTO DEL CARMEN"
},
{
"id": "01107",
"name": "ALTO HOSPICIO"
},
{
"id": "10202",
"name": "ANCUD"
},
{
"id": "04103",
"name": "ANDACOLLO"
},
{
"id": "09201",
"name": "ANGOL"
}... etc
```

### 2. Información de una Comuna

- **Método:** `GET`
- **URL:** `/township/{id}`
- **Descripción:**  Este endpoint devuelve información detallada de una comuna específica.

#### Ejemplo de Respuesta:

```
{
  "municipalityName": "SANTIAGO",
  "region": "METROPOLITANA",
  "province": "SANTIAGO",
  "rol": "69.070.100-6",
  "address": "Plaza de Armas S/N",
  "phone": "(02) 27136602 - 26333927",
  "fax": "(02) 2633 39 27",
  "web": "http://www.municipalidaddesantiago.cl",
  "email": "santiago@munistgo.cl",
  "imageUrl": "http://datos.sinim.gov.cl/files/ficha_comunal/13101/imagen_municipio",
  "authorities": {
    "mayor": {
      "name": "Irací Luiza Hassler Jacob",
      "politicalParty": "Partido Comunista de Chile - CHILE DIGNO VERDE Y SOBERANO"
    },
    "mayorPhoto": "http://datos.sinim.gov.cl/files/ficha_comunal/13101/imagen_alcalde",
    "councilors": [
      {
        "name": "Camila Fernanda Davagnino Reyes",
        "politicalParty": "Partido Comunista de Chile"
      },
      {
        "name": "Dafne Susana Concha Ferrando",
        "politicalParty": "Partido Comunista de Chile"
      },
      {
        "name": "Virginia Bernardina Palma Erpel",
        "politicalParty": "Partido Comunista de Chile"
      }...etc
```
