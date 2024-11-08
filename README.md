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
docker run --name java_db -d -p 5431:5431 -v java_data:/var/lib/postgresql/data postgres    
``` 