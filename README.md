# ExchangeCripto

## Iniciar Projeto

Para iniciar o projeto é necessário instanciar o banco de dados, pode utilizar o docker para isso:
```bash
docker build -t postgres . 
``` 
```bash
docker volume create pgdata
``` 
```bash
docker run --name java_db -d -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres    
``` 