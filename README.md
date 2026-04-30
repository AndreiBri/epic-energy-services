# Endpoint

## /auth

### POST /login

Request

```
{
    email: str
    password: str 
}
```

Esempio payload in Request

```
{
    "email": "giuseppetavella8@gmail.com",
    "password": "123456"
}

```

Response

```
{
    accessToken: str
}
```

### POST /register

Request

```
{
    email: str
    password: str 
    username: str
    nome: str
    cognome: str
}
```

Esempio payload in Request

```
{
    "email": "giuseppetavella8@gmail.com",
    "password": "123456",
    "nome": "Giuseppe",
    "cognome": "Tavella",
    "username": "tave8"
}
```

Response

```
{
    idUtente: str
}
```

## /clienti

### GET /

Response

```
una lista di clienti. se usi la paginazione, ricorda
che gli oggetti si troveranno nella proprietà "content"

{
    ....
}

```

### GET /:clienteId

Response

```
un cliente (vedere tabella o DTO relativo)
{
    
}
```

### POST /

Request

```
{
    ragioneSociale: str
    partitaIva: str
    email: str
    pec: str
    telefono: str
    emailContatto: str
    nomeContatto: str
    cognomeContatto: str
    telefonoContatto: str
    fatturatoAnnuale: number
}
```

Response

```
tutto quello in request più
{
    idClient: str
    logoAziendaleUrl: str
}
```

### DELETE /:clienteId

Request

```
<no body>
```

Response

```
<no body>
```

## /fatture

### GET /clienti/:clienteId

Request

```
<no body>
```

Response

```
vedi sotto, ricorda che è una lista
```

### POST /clienti/:clienteId

Request

```
{
    importo: number
    statoCustom: str
}
```

Response

```
tutto quello in request più
{
    idFattura: str
    dataCreazione: str
}
```

### PATCH /:fatturaId

Request

```
{
    statoCustom: str
}
```

Response

```
vedi sopra
```

### DELETE /:fatturaId

Request

```
<no body>
```

Response

```
<no body>
```

## /ruoli-custom

### POST /  (aggiungi un ruolo custom)

Request

```
{
    ruoloCustom: str
}
```


## /ruoli-custom

### GET /

Request

```
<no body>
```

Response

```
[
    {
        idRuoloCustom: str
        ruoloCustom: str   
    }
]

```

### POST /:ruoloCustomId/utenti/:utenteId  (aggiungi un'associazione tra un ruolo custom e un utente)

Non ritorniamo niente nel payload della risposta, perché tutto quello 
che mandiamo è abbastanza. Non ci serve avere niente dopo.

Request

```
<no boby>
```

Response

```
<no body>
```


### GET /utenti/:utenteId (ottieni i ruoli custom di un utente)

Request

```
<no body>
```

Response

```
lista di ruoli custom
```

## /sedi

### POST /clienti/:clienteId/comuni/:comuneId

Request

```
{
    tipoSede: str
    ecc.
}
```

Response

```
vedi sopra
```

## /comuni

### GET /

Request

Query params:
nomeComune

Response

```
content: [
    {
        idComune: str
        idProvincia: str
        nomeComune: str
    }
]
```

endpoint = verbo + url relativa
GET /utenti

richiesta
nessuna richiesta

risposta

può essere paginato
[
{
UUID idUtente,
String username,
String email,
String nome,
String cognome,
String avatarUrl,
RuoloUtente ruolo
}
]

# PERMESSI

FEATURE | USER | ADMIN
—--------------------------------------------------------------------------------------
lettura SI SI
inserire clienti SI SI
tutto il resto NO SI

tutto il resto = aggiungi sede, aggiungi ruoli custom, aggiungi fatture (aggiungi nel senso di modifica/ aggiunta/
rimozione)
