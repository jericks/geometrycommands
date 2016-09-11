union
=====

**Name**:

geom union

**Description**:

Calculate the union between two geometries.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom union -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))"