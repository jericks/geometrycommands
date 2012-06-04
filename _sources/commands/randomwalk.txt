randomwalk
==========

**Name**:

geom randomwalk

**Description**:

Generate a random walk as a linestring.

**Arguments**:

   * -n --number: The number of walks

   * -d --distance: The distance between Coordinates

   * -p --probability: The probability of changing direction

   * -a --angle: The angle increment (in degrees) when changing direction

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom randomwalk -n 100 -a 45 -d 10 -g "POINT (1 1)"