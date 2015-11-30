**Lien du TD : http://www.machada.fr/courses/bdd_client_td_1.pdf**

**Lien API GitHub : https://developer.github.com/v3/**

## Question 1

### API GITHUB
Services disponibles :
  - Profil utilisateur
  - Information sur un utilisateur après connexion (OAuth2)
  - Information sur un repository
  - Faire une recherche
  - Lister des requêtes Pull
  - Afficher les problèmes d'un projet
  
### API OPENWHEATHERMAP
Services disponibles :
  - Météo par ville
  - Météo sur 16 jours
  - Données d'une station météo
  - Index UV d'un lieux choisi
  - Données météos archivées d'une ville

## Question 2 (Avec l'API GitHub)

### Lister des requêtes POST :
  - Créer une requête Pull : *POST /repos/:owner/:repo/pulls*
  - Créer un problème (issue) : *POST /repos/:owner/:repo/issues*
  - Créer un repos sur un utilisateur authentifié : *POST /user/repos*
  - Créer un repos dans une organisation : *POST /orgs/:org/repos*
  - Créer un Fork d'un gist : *POST /gists/:id/forks*

### Lister des requêtes GET :
  - Lister les gists d'un utlisateur : *GET /users/:username/gists*
  - Créer un gist : *GET /gists/:id*
  - Afficher les problèmes d'un projet : *GET /issues*
  - Lister les organisations d'un utilisateur : *GET /users/:username/orgs*
  - Lister les repos d'un utilisateur : *GET /users/:username/repos*

### Faire des requêtes authentifiés
Les requêtes authentifiées se font grâce à un *token* généré dans son compte GitHub

Par exemple pour avoir les followers d'un utilisateur, on lance une requête comme celle-ci:

https://www.getpostman.com/oauth2/start?authorization_url=https%3A%2F%2Fgithub.com%2Flogin%2Foauth%2Fauthorize&access_token_url=https%3A%2F%2Fgithub.com%2Flogin%2Foauth%2Faccess_token&client_id=1419684&client_secret=d3e44e565f25fda093c26f5297641c4df38ad643&scope=&local_access_token=false&app_id=fhbjgbiflinjbdggehcddcbncdddomop

Dans Postman, on précise:
  - l'URL d'autorisation de Github : *https://github.com/login/oauth/authorize*
  - L'URL Access token : https://github.com/login/oauth/access_token
  - L'id de l'utilisateur (*1419684* ici) 
  - Le token : *d3e44e565f25fda093c26f5297641c4df38ad643* par exemple

Il va être demandé à l'utilisateur de se connecter. Une fois connecté on est redirigé sur une url de callback et on est autorisé à voir le nombre de followers (par exemple)


## Tester les requêtes
Les requêtes avec PostMan fonctionnent, par exemple pour avoir des informations en JSON sur un utilisateur, il faut faire la requête : **https://api.github.com/users/victr**.

**On obtient la réponse suivante :**

> {
  "login": "victr",
  "id": 1419684,
  "avatar_url": "https://avatars.githubusercontent.com/u/1419684?v=3",
  "gravatar_id": "",
  "url": "https://api.github.com/users/victr",
  "html_url": "https://github.com/victr",
  "followers_url": "https://api.github.com/users/victr/followers",
  "following_url": "https://api.github.com/users/victr/following{/other_user}",
  "gists_url": "https://api.github.com/users/victr/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/victr/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/victr/subscriptions",
  "organizations_url": "https://api.github.com/users/victr/orgs",
  "repos_url": "https://api.github.com/users/victr/repos",
  "events_url": "https://api.github.com/users/victr/events{/privacy}",
  "received_events_url": "https://api.github.com/users/victr/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Victor",
  "company": null,
  "blog": null,
  "location": null,
  "email": null,
  "hireable": null,
  "bio": null,
  "public_repos": 0,
  "public_gists": 0,
  "followers": 0,
  "following": 0,
  "created_at": "2012-02-08T13:45:21Z",
  "updated_at": "2015-09-04T14:33:25Z"
}

## Conception Application Cliente

### Lister les fonctionnalités applicatives correspondant aux requêtes et les classer selon les deux types de clients : Mobile et Web.

> On peut imaginer créer un site web et son application qui permettent d'avoir des statistiques sur son profil GitHub.
> Le site et l'application s'appelleront *GitStats*

Toutes les fonctionnalités listées peuvent appartenir à la fois à un client Web ou Mobile :

  - Lister les gists d'un utlisateur
  - Lister les problèmes d'un projet
  - Lister les organisations d'un utilisateur
  - Lister les repos d'un utilisateur
 
###  Faire des ébauches d’interfaces possibles pour deux applications clientes, une Mobile et une Web.

### Lien des captures d'écran:
  - Maquette web : ![alt description](http://s29.postimg.org/3zbhdimfb/Screenshot_2015_11_12_16_02_30.png)
  - Maquette mobile : ![alt description](http://s4.postimg.org/e4lefv2gd/Screenshot_2015_11_12_16_02_11.png)

