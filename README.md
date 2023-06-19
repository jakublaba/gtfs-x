# GTFS parser
This project is aimed to provide a flexible open-source tool for interacting with data from gtfs feeds. \
Name *"gtfs-x"* is pronounced "gtfs-ex", meaning "gtfs explorer".

## Origin of the idea
Idea for this library is actually a byproduct of our other project, where we encountered difficulties interacting with gtfs data and weren't able to find any well-documented library for this task, so we were forced to write all the logic by hand. \
We figured - why not improve upon our works and share a useful tool with the world?

## Philosophy
Naming convention is designed to mirror [gtfs specification](https://gtfs.org/schedule/reference) as closely as possible. Even when some field could be named better, we decide to keep it consistent with specification.
The reason for that decision, is for users to be able to refer to the official docs more easily, rather than having to decode renamed fields first.

## Test data

- `sample-feed-1.zip` - from [gtfs.org](https://gtfs.org)
