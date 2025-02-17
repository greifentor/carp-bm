# CARP-BM
An application for pen and paper role playing battle maps.


## Planned Functionality

* Manage maps & tokens.
* Provide REST endpoints for battle map manipulation.
* Provide REST endpoints to get player views.


## Requirements

### Run

**Java** 17


### Build

**Java** 17

**Maven** Maven 3.9.9


### Start

* Build the project in the main folder with `mvn clean install -Pproduction`.
* Start the rest-server application by `java -jar rest-server\target\carp-bm-rest-server-[version].jar`
* Start the shell application by `java -jar rest-server\target\carp-bm-shell-[version].jar`
* Create and set some tokens via the shell comands.
* Create a battle map via shell command.
* Start the swing gui application by `java -jar rest-server\target\carp-bm-swing-[version].jar`
* Click a token and click a free position on the map. Token should move to the new field.

## Architecture

For details look here: [architecture documentation](docs/architecture/architecture.md)
