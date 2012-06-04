locatepoint
===========

**Name**:

geom locatepoint

**Description**:

Locate the position of a point on the linear geometry as a percentage of the distance.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom locatepoint -g "LINESTRING (0 0, 5 5, 10 10)" -o "POINT (2.5 2.5)"