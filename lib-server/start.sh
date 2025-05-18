java \
-Dserver.port=8081 \
-Dspring.datasource.url=jdbc:mariadb://localhost:3306/carpbmlib \
-Dspring.datasource.driverClassName=org.mariadb.jdbc.Driver \
-Dspring.datasource.username=carpbmlib \
-Dspring.datasource.password=password \
-Dsecurity.service.secret=secret \
-jar target/carp-bm-lib-server-0.0.1.jar
