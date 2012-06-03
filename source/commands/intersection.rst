intersection
============

**Name**:

geom intersection

**Description**:

Calculate the intersection between two geometries.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom intersection -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((5 5, 5 15, 15 15, 15 5, 5 5))"