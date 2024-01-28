# Lavender

Lavender (earlier [Bastom](https://github.com/AndusDEV/bastom)) is a [Minestom](https://github.com/minestom/minestom) minecraft server software containing some basic features that could be useful.

This is LavenderBloom. This edition of Lavender allows you to load Minecraft worlds using standard minecraft format Anvil or use [Polar](https://github.com/hollow-cube/polar) by Hollow Cube

## Table of Contents

- [Install](#install)
- [Usage](#usage)
- [API](#api)
- [Maintainers](#maintainers)
- [License](#license)

## Install
You could either just download a [release](https://github.com/LavenderPowered/LavenderBloom/releases) or you compile the server yourself using the following commands under Linux
```shell
git clone https://github.com/LavenderPowered/LavenderBloom.git
cd LavenderBloom
./gradlew build
```
The server jar will be located at `build/libs/Lavender-<VERSION>.jar`.

Note that for compiling you need to use a JDK 17.

## Usage
To run the server you need to have a Java 17 runtime installed.
Use the following command to start the server for the first time.
```shell
java -jar Lavender-<VERSION>.jar
```
This generates a `start.sh` script and a settings file with these default values:
```json5
{
  "SERVER_IP": "localhost",
  "SERVER_PORT": 25565,
  "MODE": "OFFLINE",
  "VELOCITY_SECRET": "",
  "TPS": "20",
  "CHUNK_VIEW_DISTANCE": "8",
  "ENTITY_VIEW_DISTANCE": "5",
  "TERMINAL_DISABLED": false,
  "EXTS_COMMAND_FOR_PLAYERS": false,
  "OPEN_TO_LAN": false
}
```
There is also a config file for worlds. In this edition there is only flat instance, to maintain the simplicity of this server:
```json5
{
  "ENABLE_INSTANCE": false,
  // FLAT, ANVIL, POLAR, POLAR_CONVERT
  // If world type is set to POLAR_CONVERT it will try to convert all directories in "polar_conversion" directory (needs to be created manually) to .polar format.
  "WORLD_TYPE": "FLAT",
  // Ex. ["world1", "world2"] assuming they're in the root directory of the server, if not add folders ex. ["worlds/world1"]
  "WORLD_LOCATIONS": []
}
```
You have to restart the server for changes in there to take effect.

Note that this server only supports 1.20.1 clients on version 1.0.0. To allow other/multiple versions to connect you need to use a proxy (ex. Velocity) with plugins like ViaVersion.

### Restarting
Restarting the server calls the `./start.sh` script.
The generated script will restart the server with no way to access the console.
So keep in mind that you will need an extension providing remote access or use tmux/screen in the `start.sh` to access the console.


## API
This server itself does not add some API. But it features [Minestom's API](https://github.com/Minestom/Minestom) so you can use it from within extensions.

## Maintainers

 - [@andusdev](https://github.com/andusdev)
 - [Original (microstom)](https://github.com/KlainStom/microstom)

## License

This project is licensed under the [MIT License](LICENSE).
