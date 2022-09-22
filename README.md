# Prueba Técnica

Microservicios Client, Accountm y Movement.

## Installation

Se necesita descargar una imagen de MySql y además crear las imagenes con el .jar   

```bash
docker pull mysql
```

## Usage

Image MySql debe estar en 'mire' y asignar user: root, pass: 123456, create bd: client / account / movement

# image y container 'client'
docker build -t client:1.0 .
docker create -p4000:4000 --name client --network mired client:1.0

# image y container 'account'
docker build -t account:1.0 .
docker create -p4001:4001 --name account --network mired account:1.0

# image y container 'movement'
docker build -t movement:1.0 .
docker create -p4001:4001 --name movement --network mired movement:1.0