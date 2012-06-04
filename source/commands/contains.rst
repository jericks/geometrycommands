contains
========

**Name**:

geom contains

**Description**:

Calculate whether the first geometry contains the other geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom contains -g "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))" -o "POINT (2 2)"