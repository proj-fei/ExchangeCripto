# PostgreSQL 16
FROM postgres:16

#  Variáveis de ambiente
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=postgres


# COPY ./init-db.d /docker-entrypoint-initdb.d/

# Exponha a porta padrão do PostgreSQL
EXPOSE 5432
