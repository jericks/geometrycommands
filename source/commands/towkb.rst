towkb
=====

**Name**:

geom towkb

**Description**:

Write a Geometry to WKB.

**Arguments**:

   * -d --dimension: The output dimension (2 or 3)

   * -b --byte-order: The byte order (1 = big endian, 2 = little endian)

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom towkb -g "POINT (10 10)"