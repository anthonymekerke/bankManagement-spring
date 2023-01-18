# BankManagement - BACKEND

## Description

### Contexte

Période: Octobre 2022 - Janvier 2023

### Énoncé

### Dérouler

**Format des données:**
<table>
    <tr>
        <td><b>Table name</b></td><td>ID</td><td>...</td>
    </tr>
</table>

**Liste des URI (endpoints):**

```
 [VERBE] /register
```
Permet aux utilisateurs de se connecter (à implémenter en même temps que la securité dans un loginController)

```
  /accounts[?client_id] -> show the accounts of the connected user
```
Accède aux **Compte** du **Client** connecté (Le paramètre *client_id* n'est par forcément utile -> essayer sans)

```
  /accounts/{id}
```
Accède au résumé du **Compte** avec l'identifiant donné (si appartient au **Client** connecté)

```
  /accounts/{id}/transactions
```
Accède aux dernières **Transaction** du **Compte** avec l'identifiant donné (si appartient au **Client** connecté)

```
  /transactions/{id}
```
Accède aux détails de la **Transaction** donné (si appartient au **Client** connecté)

```
  /saving-accounts/{id}
```
Accède aux détails du **Compte Épargne** donné (si appartient au **Client** connecté)

```
  /current-accounts/{id}
```
Accède aux détails du **Compte Courant** donné (si appartient au **Client** connecté)

**Verbes HTTP:**

GET(ressource): param -> _ ; return -> List<BasicDTO>
GET(ressource/{id}): param -> id ; return -> FullDTO
POST(ressource): param -> FullDTO ; return -> ResponseEntity<?>
PUT(ressource/{id}): param -> id, FullDTO ; return -> ResponseEntity<BasicDTO>
DELETE(ressource/{id}): param -> id ; return -> ResponseEntity<BasicDTO>

### Images

[image](screenshots/[name] "hover")

### Fonctionnalitées

#### Fait

* Func 1
* Func 2
	* Func 2.1
	* Func 2.2

#### En cours

#### À faire / Souhaitées (Idées)

## Utilisation

### Dépendances

<table>
  <thead>
    <tr><th colspan="2">Environnement Technique</th></tr>
  </thead>
  <tbody>
    <tr><td><b>SGBD</b></td><td></td></tr>
    <tr><td><b>Langage</b></td><td></td></tr>
    <tr><td><b>Technologies/Logiciels</b></td><td></td></tr>
    <tr><td><b>Normes & architectures</b></td><td></td></tr>
	<tr><td><b>Méthodes</b></td><td></td></tr>
  </tbody>
</table>

### Installation / Compilation

```bash
git clone "nom du depot"
```

### Lancement (run)

#### Help

erreurs qui peuvent arriver, comment les résoudres

### À savoir

Inspiration, contexte développement, ressources
