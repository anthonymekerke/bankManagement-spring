# BankManagement - BACKEND

required dependencies:
- Config Client
- Spring Web
- Spring Devtools
- Spring Actuator

Custom dependencies:
- Spring HATEOAS
- MySQL Driver
- Spring Data JPA
- Spring Security | Oauth Server 2
- Validation

## Description

### Contexte

Période: Octobre 2022 - Janvier 2023

### Énoncé

### Dérouler

**Format des données:**
<table>
    <tr>
        <td><b>Client</b></td><td>ID</td><td>LASTNAME</td><td>FIRSTNAME</td><td>LOGIN</td><td>PASSWORD</td><td>EMAIL</td><td>ACCESSION_DATE</td>
    </tr>
    <tr>
      <td><b>Compte</b></td><td>ID</td><td>IBAN</td><td>LABEL</td><td>CREATION_DATE</td><td>FK_CLIENT</td>
    </tr>
    <tr>
      <td><b>Compte Courant</b></td><td>BANK_OVERDRAFT</td><td>FK_ACCOUNT</td>
    </tr>
    <tr>
      <td><b>Compte Épargne</b></td><td>INTEREST_RATE</td><td>FK_ACCOUNT</td>
    </tr>
    <tr>
      <td><b>Transaction</b></td><td>ID</td><td>WORDING</td><td>PAYMENT</td><td>WITHDRAW</td><td>EXECUTION_DATE</td><td>VALUE_DATE</td><td>BALANCE</td><td>FK_ACCOUNT</td>
    </tr>
</table>

**Liste des URI (endpoints):**

```
 POST /authenticate
```
Sert à l'authentification d'un utilisateur. Lorsque les identifiants fournies (HTTP Header -> Authorization: Basic \<username:password\>) sont validés, l'authentification se fait par la suite à l'aide d'un jeton JWT (HTTP Header -> Authorization: Bearer \<token\>).

```
 GET /accounts
```
Accède a la liste des **Compte** du **Client** connecté

```
 GET /accounts/{id}
```
Accède au détails du **Compte** identifié (si appartient au **Client** connecté)

```
 GET /accounts/{id}/transactions
```
Accède à l'ensemble des **Transaction** du **Compte** identifié (si appartient au **Client** connecté)

```
 POST /accounts/{id}/transactions
```
Créée une nouvelle **Transaction** associée au **Compte** identifé (si appartient au **Client** connecté)

```
 GET /accounts/{ac_id}/transactions/{tr_id}
```
Accède au détails de la **Transaction** identifié associée au **Compte** identifié (si appartient au **Client** connecté)

```
 GET /saving-accounts/{id}
```
Accède aux détails du **Compte Épargne** identifié (si appartient au **Client** connecté)

```
 GET /current-accounts/{id}
```
Accède aux détails du **Compte Courant** identifié (si appartient au **Client** connecté)

### Fonctionnalitées

#### Fait

- Concevoir la base de donnée (MCD, MPD, MDD)
- Implémenter la partie business
- Implémenter les endpoints
- Mettre en place la sécurité (JWT, CORS)
- Ajouter les règles de validations des DTO
- Gérer les exceptions et le feedback pour l'utilisateur
- Mettre en place l'authentification

#### En cours

- Transformer l'application monolithe en microservices

#### À faire / Souhaitées (Idées)

- Tester les endpoints
- Test unitaires
- Corriger la securité CSRF
- Écrire la documentation
- Mettre en place la feature "Budget"
- Mettre en place une navigation "hypermedia-driven" ?

## Utilisation

### Dépendances

<table>
  <thead>
    <tr><th colspan="2">Environnement Technique</th></tr>
  </thead>
  <tbody>
    <tr><td><b>SGBD</b></td><td>MySQL</td></tr>
    <tr><td><b>Langage</b></td><td>Java 11</td></tr>
    <tr><td><b>Technologies/Logiciels</b></td><td>Spring 2.7, Maven 3.0</td></tr>
    <tr><td><b>Normes & architectures</b></td><td>3-Tiers, Rest, JWT</td></tr>
  </tbody>
</table>

### Installation / Compilation

### Lancement

#### Help

erreurs qui peuvent arriver, comment les résoudres

### À savoir

Inspiration, contexte développement, ressources
