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
    "ragioneSociale": "Azienda Test SRL",
    "partitaIva": "12345678801",
    "email": "test1@azienda.it",
    "pec": "test1@pec.it",
    "telefono": "0212345858",
    "emailContatto": "luca@azienda.it",
    "nomeContatto": "Luca",
    "cognomeContatto": "Verdi",
    "telefonoContatto": "3331234567",
    "fatturatoAnnuale": 50000,
    "formaGiuridica": "PA"
}
```

Response

```
{
      UUID idCliente,
        String ragioneSociale,
        String partitaIva,
        String email,
        String pec,
        String telefono,
        String emailContatto,
        String nomeContatto,
        String cognomeContatto,
        String telefonoContatto,
        BigDecimal fatturatoAnnuale,
        String logoAziendaleUrl,
        FormaGiuridica formaGiuridica
}
```

### PATCH (Solo ADMIN) /:clienteId

Se si cambia logo URL e : /:clienteId/logo
Request

Body bisgona mettere form-data per cambiare logo
key : logo
value: file

```
{
   Mettere il dato o i dati che che si vogliono cambiare 
}
```

Response

```
{
    "idCliente": "uuid...",
    "ragioneSociale": ".....",
    "partitaIva": "....",
    "email": "...",
    "pec": "...",
    "telefono": ".....",
    "emailContatto": "...",
    "nomeContatto": "...",
    "cognomeContatto": "...",
    "telefonoContatto": "...",
    "fatturatoAnnuale": ...,
    "logoAziendaleUrl": "....",
    "formaGiuridica": "..."
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

Response

```
stesso di richiesta più
{
    idRuoloCustom: str
}
```

### POST /:ruoloCustomId/utenti/:utenteId  (aggiungi un'associazione tra un ruolo custom e un utente)

Esempio di richiesta:

`/ruoli-custom/sdasd-34234-324sds-343243/utenti/sakdjkda-asdasdasd-asdasd-asdas`

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

# PERMESSI

FEATURE | USER | ADMIN
—--------------------------------------------------------------------------------------
lettura SI SI
inserire clienti SI SI
tutto il resto NO SI

tutto il resto = aggiungi sede, aggiungi ruoli custom, aggiungi fatture (aggiungi nel senso di modifica/ aggiunta/
rimozione)
