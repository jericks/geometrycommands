touches
=======

**Name**:

geom touches

**Description**:

Determine if the input geometry touches another geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom touches -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))"