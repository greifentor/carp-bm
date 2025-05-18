# CARP-BM
An application for pen and paper role playing battle maps.


## Planned Milestones

### Milestone 1
* Manage maps & tokens:
	* Add, remove and edit battle maps.
	* Add, remove and edit tokens.
	* Import tokens from Carp-BM web server.
* Shows maps and tokens in two modes:
	* GM Mode with controls for:
		* Open and close a battle map.
		* Add and remove tokens to a battle map.
		* Set initiative and hit points for tokens.
	* Player mode
* A web server which provides tokens.

### Milestone 2
* Web client for players view.

### Milestone 3
* Connection to Carp-CM-5e


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
