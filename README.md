# Prueba Técnica

Microservicios Customer, Account y Movement.

## Instalación

Se necesita descargar una imagen de MySql y además crear las imagenes con el .jar   

```bash
docker pull mysql
```

## Uso

Image MySql debe estar en la red **'mired'** y asignar **user:** root, **pass:** 123456, create **bd:** customer/account/movement

```python
import foobar

# generar .jar microservices
mvn -DskipTests package

# image y container 'Customer'
docker build -t customer:1.0 .
docker create -p4000:4000 --name customer --network mired customer:1.0

# image y container 'account'
docker build -t account:1.0 .
docker create -p4001:4001 --name account --network mired account:1.0

# image y container 'movement'
docker build -t movement:1.0 .
docker create -p4002:4002 --name movement --network mired movement:1.0
