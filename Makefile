build:
	mvn clean verify

run:
	mvn -P cargo.run -D repo.path=storage