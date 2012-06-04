within
======

**Name**:

geom within

**Description**:

Determine if the input geometry is within the other geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom within -g "POINT (2 2)" -o "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))"