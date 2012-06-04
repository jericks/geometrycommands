overlaps
========

**Name**:

geom overlaps

**Description**:

Determine whether the first geometry overlaps with the other geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom overlaps -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))"