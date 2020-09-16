[![CircleCI](https://circleci.com/gh/sankarvijay/myNanterreM2.svg?style=svg)](https://circleci.com/gh/sankarvijay/myNanterreM2)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b55330b80abb423bac3057216091bb04)](https://www.codacy.com/manual/sankarvijay/myNanterreM2?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sankarvijay/myNanterreM2&amp;utm_campaign=Badge_Grade)
[![GitHub issues](https://img.shields.io/github/issues/sankarvijay/myNanterreM2.svg)](https://github.com/sankarvijay/myNanterreM2/issues)
[![License](https://img.shields.io/github/license/sankarvijay/myNanterreM2.svg?style=flat-square)](LICENSE.md)
[![Version](https://img.shields.io/github/release/sankarvijay/MyNanterreM2.svg?label=version&style=flat-square)](build.gradle)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)

# MyNanterre

**MyNanterre**, l'application pour une meilleure vie étudiante à Nanterre. 

## Qu'est ce que c'est ?

Une application qui permet de founir aux étudiants des services relatifs au campus de l'université de Nanterre.
MyNanterre est une application disponible sous **Android**.

MyNanterre est une application qui vous permettra de :

-Vous voulez venir à la fac ou rentrer chez vous ? Vous trouverez les prochains départs sur le RER A et la ligne L avec l'état du trafic, tout cela avec des notifications en temps réel.

-Vous voulez manger ? Visualisez l'affluence sur les différents points de restauration du CROUS ouverts à l'Université ainsi que la disponibilité des produits, la carte des prix mais aussi laffluence des différentes cafétariats.

-Vous voulez faire du sport ? Programmez vos propres séances de sports et les étudiants pourront s'y inscrire, vous pourrez découvrir les sports disponibles à Nanterre et leurs horaires.

-Vous voulez étudier ? Consultez l'application pour connaître les horaires, la localisation et l'affluence des bibliothèques du campus. Vous pourrez aussi vous diriger vers ces bibliothèques grâce à une aide de navigation.

-Vous êtes nouveaux et perdus sur le campus de l'université ? Consultez notre carte en 3D avec sa navigation en temps réel pour vous diriger au sein du campus.

-Vous voulez suivre l'actualité de l'Université : vous pourrez consulter les dernières infos sur l'université de Nanterre avec les Lives Tweets.

-Vous pouvez récupérer la dernière version de l'application depuis la rubrique "Mise à jour".

Pour avoir plus d'informations, visitez notre site web : https://sankarvijay.github.io/myNanterreM2/

## Télécharger 

L'application peut être téléchargée 

- soit via l'url : <a href="https://github.com/sankarvijay/myNanterreM2/releases/download/v2.0/myNanterre_v2.0.apk">/releases/download/v2.0/myNanterre_v2.0.apk</a> 
- soit via le QR Code : 

<a rel='nofollow' href='https://www.qrcode-generator.de/
            ' border='0' style='cursor:default'><img src='https://chart.googleapis.com/chart?cht=qr&chl=https%3A%2F%2Fgithub.com%2Fsankarvijay%2FmyNanterreM2%2Freleases%2Fdownload%2Fv2.0%2FmyNanterre_v2.0.apk&chs=180x180&choe=UTF-8&chld=L|2' alt=''></a>

## Système de Build
* [Gradle](https://gradle.org/)

### Compiling

```shell
cd myNanterre/
./gradlew
ou sur windows :
./gradlew.bat

et enfin :

adb install -r myNanterre.apk

```
## Installation

### Prérequis

Avant de pouvoir utiliser notre projet, il faut installer certains outils.

#### Installer le SDK

- Télécharger et installer le SDK : http://www.oracle.com/technetwork/java/javase/downloads/index.html

  
#### Installer Git

- Télécharger et installer Git : [Télécharger Git](https://gitforwindows.org/)

#### Installer Android Studio

- Télécharger et installer Git : [Télécharger Android Studio](https://developer.android.com/studio/install)

## Deux manières de lancer l'application

### Via l'émulateur d'Android Studio

Suivre ce tuto : [Tutoriel AVD Manager](http://vogella.developpez.com/tutoriels/android/installation-outils-developpement/#L5)

#### Cloner MyNanterre

Une fois Git installé, il suffit de suivre les instructions ci-dessous.

git clone https://github.com/sankarvijay/myNanterreM2.git

### Via votre mobile

Depuis votre mobile Android, récupérez l'apk sur notre site web https://sankarvijay.github.io/myNanterreM2/. Puis, autorisez l'installation. Une fois installé, vous verrez dans votre menu, l'application myNanterre.

Et voila ! le projet est prêt pour utilisation.

## Auteurs
* **SANKAR Vijay** - [Github](https://github.com/sankarvijay)
* **VIEIRA Dorian** - [Github](https://github.com/dorianvieira)
* **BAGHRAR El Hassan** - [Github](https://github.com/ElHassanBaghrar)

## Licences
MyNanterre©: Copyright 2020 Vijay.S, Dorian.V and El Hassan.B; Apache License v2.0 https://www.apache.org/licenses/

-   Données Open data : [RATP](https://github.com/pgrimaud/horaires-ratp-api) sous [(licences)](https://github.com/pgrimaud/horaires-ratp-api/blob/master/LICENSE)
-   Données Open data : [STIF](https://opendata.stif.info/page/licences/) sous [(licences)](https://opendata.stif.info/page/licences/)
-   Données Open data : [Batiments API](https://opendata.hauts-de-seine.fr/explore/dataset/affectations-et-usages-des-batiments-de-luniversite-paris-nanterre/table/?sort=campus&rows=40&dataChart=eyJxdWVyaWVzIjpbeyJjaGFydHMiOlt7InR5cGUiOiJjb2x1bW4iLCJmdW5jIjoiQ09VTlQiLCJ5QXhpcyI6InN1cmZhY2Vfc2hvbiIsInNjaWVudGlmaWNEaXNwbGF5Ijp0cnVlLCJjb2xvciI6IiM2NmMyYTUifV0sInhBeGlzIjoiYW5uZWVfZGVfY29uc3RydWN0aW9uIiwibWF4cG9pbnRzIjoiIiwidGltZXNjYWxlIjoieWVhciIsInNvcnQiOiIiLCJjb25maWciOnsiZGF0YXNldCI6ImFmZmVjdGF0aW9ucy1ldC11c2FnZXMtZGVzLWJhdGltZW50cy1kZS1sdW5pdmVyc2l0ZS1wYXJpcy1uYW50ZXJyZSIsIm9wdGlvbnMiOnsic29ydCI6ImNhbXB1cyIsInJvd3MiOiI0MCJ9fX1dLCJ0aW1lc2NhbGUiOiIiLCJkaXNwbGF5TGVnZW5kIjp0cnVlLCJhbGlnbk1vbnRoIjp0cnVlfQ%3D%3D&location=17,48.90371,2.21429&basemap=jawg.streets) sous [(licences)](https://opendata.hauts-de-seine.fr/explore/dataset/affectations-et-usages-des-batiments-de-luniversite-paris-nanterre/table/?sort=campus&rows=40&dataChart=eyJxdWVyaWVzIjpbeyJjaGFydHMiOlt7InR5cGUiOiJjb2x1bW4iLCJmdW5jIjoiQ09VTlQiLCJ5QXhpcyI6InN1cmZhY2Vfc2hvbiIsInNjaWVudGlmaWNEaXNwbGF5Ijp0cnVlLCJjb2xvciI6IiM2NmMyYTUifV0sInhBeGlzIjoiYW5uZWVfZGVfY29uc3RydWN0aW9uIiwibWF4cG9pbnRzIjoiIiwidGltZXNjYWxlIjoieWVhciIsInNvcnQiOiIiLCJjb25maWciOnsiZGF0YXNldCI6ImFmZmVjdGF0aW9ucy1ldC11c2FnZXMtZGVzLWJhdGltZW50cy1kZS1sdW5pdmVyc2l0ZS1wYXJpcy1uYW50ZXJyZSIsIm9wdGlvbnMiOnsic29ydCI6ImNhbXB1cyIsInJvd3MiOiI0MCJ9fX1dLCJ0aW1lc2NhbGUiOiIiLCJkaXNwbGF5TGVnZW5kIjp0cnVlLCJhbGlnbk1vbnRoIjp0cnVlfQ%3D%3D&location=17,48.90371,2.21429&basemap=jawg.streets)
-    MPAndroidChart : [Histogramme affluence](https://github.com/PhilJay/MPAndroidChart) Copyright 2019 Philipp Jahoda; sous [(licences)](https://www.apache.org/licenses/)
-    Chart.js : [Histogramme bibliothèque](https://www.chartjs.org/) Copyright 2018 Chart.js Contributors; sous [(licences)](https://opensource.org/licenses/MIT)
-    Twitter Kit : [Tweets API](https://github.com/twitter-archive/twitter-kit-android) Copyright 2017 Twitter, Inc; sous [(licences)](https://www.apache.org/licenses/)
-    Google Maps : [Plan](https://console.developers.google.com/apis) Copyright 2020 Google, Inc; sous [(licences)](https://www.cloud.google/maps-platform/terms)
-    Open Street Map : [Bibliothèques](https://www.openstreetmap.org/#map=6/46.449/2.210) Copyright 2020 OpenStreetMap contributors OSM-Fr map data; sous [(licences)](https://www.openstreetmap.org/copyright)
-    Icônes : Toutes les icones de notre application sont tirées du site : https://icones8.fr/
