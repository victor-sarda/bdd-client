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

## Tester les requêtes


