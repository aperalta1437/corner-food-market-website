mkdir -p "E:\Users\AMigu\docker\volumes\corner-food-market-website-pg-db"

docker run --rm --name corner-food-market-website-pg-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v E:\Users\AMigu\docker\volumes\corner-food-market-website-pg-db:/var/lib/postgresql postgres
Start-Sleep 3
$env:PGPASSWORD=postgres
psql -U postgres -d dev -h localhost -f schema.sql
psql -U postgres -d dev -h localhost -f triggers.sql
psql -U postgres -d dev -h localhost -f data.sql