coveredby
=========

**Name**:

geom coveredby

**Description**:

Determine whether the first geometry is covered by the other geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom coveredby -g "POINT (2 2)" -o "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))"