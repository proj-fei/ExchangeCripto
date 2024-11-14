# ExchangeCripto

## Iniciar Projeto

Para iniciar o projeto é necessário instanciar o banco de dados, pode utilizar o docker para isso:
```bash
docker build -t postgres . 
``` 
```bash
docker volume create java_data
``` 
```bash
docker run --name java_db -d -p 5432:5432 -v java_data:/var/lib/postgresql/data postgres    
``` 