docker-compose down

cd bc-yahoo-finance
mvn clean install -Pdev

docker build -t bc-yahoo-finace:0.0.1 -f Dockerfile .

cd ../bc-stock-web
mvn clean install

docker build -t bc-stock-web:0.0.1 -f Dockerfile .
cd ..

docker-compose up -d