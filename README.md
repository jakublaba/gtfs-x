# GTFS parser

This project is aimed to provide a flexible open-source tool for mapping gtfs feeds to POJOs

## Origin of the idea
Idea for this library is a byproduct of our other project, where we encountered difficulties interacting with gtfs data and weren't able to find any well-documented library for this task, so we were forced to write all the logic by hand. \
We figured - why not improve upon our works and share a (hopefully) useful tool with the world?

## Naming convention
Naming convention is designed to mirror [gtfs specification](https://gtfs.org/schedule/reference) as closely as possible. Even when some field could be named better, we decide to keep it consistent with specification.
The reason for that decision, is for users to be able to refer to the official docs more easily, rather than having to decode renamed fields first.

## Javadocs
Javadocs for POJOs are either a copy of [gtfs specification](https://gtfs.org/schedule/reference), or shortened version.
File names are replaced with names of entities from this library.
Each POJO javadoc links to the official docs.
