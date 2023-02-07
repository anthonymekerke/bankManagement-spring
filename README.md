# BankManagement - BACKEND

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
 GET /authenticate
```
Sert à l'authentification d'un utilisateur. Lorsque les identifiants fournies sont validés, l'authentification se fait par la suite à l'aide d'un jeton JWT.

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
 POST /transactions
```
Créée une nouvelle **Transaction** pour le client connecté

```
 GET /transactions/{id}
```
Accède aux détails de la **Transaction** identifié (si appartient au **Client** connecté)

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

#### En cours

- Ajouter les règles de validations des DTO
- Gérer les exceptions et le feedback pour l'utilisateur

#### À faire / Souhaitées (Idées)

- Tester les endpoints
- Corriger la securité CSRF
- Mettre en place le endpoints '/authenticate', pour valider les identifiants données et poursuivre avec JWT (plutot que /client)
- Écrire la documentation
- Mettre en place la feature "Budget"

## Utilisation

### Dépendances

<table>
  <thead>
    <tr><th colspan="2">Environnement Technique</th></tr>
  </thead>
  <tbody>
    <tr><td><b>SGBD</b></td><td>MySQL</td></tr>
    <tr><td><b>Langage</b></td><td>Java</td></tr>
    <tr><td><b>Technologies/Logiciels</b></td><td>Spring</td></tr>
    <tr><td><b>Normes & architectures</b></td><td>3-Tiers, Rest, JWT</td></tr>
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
