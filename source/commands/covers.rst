covers
======

**Name**:

geom covers

**Description**:

Determine whether the first geometry covers the other geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom covers -g "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))" -o "POINT (2 2)"