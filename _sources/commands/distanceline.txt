distanceline
============

**Name**:

geom distanceline

**Description**:

Generate a LineString representing the shortest distance between two geometries.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom distanceline -g "POINT (5 5)" -o "POINT (20 21)"