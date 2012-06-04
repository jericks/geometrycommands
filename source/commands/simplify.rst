simplify
========

**Name**:

geom simplify

**Description**:

Simplify the input geometry.

**Arguments**:

   * -a --algorithm: The distance tolerance (douglaspeucker/dp or topologypreserving/tp)

   * -d --distance: The distance tolerance

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom simplify -g "LINESTRING (1 1, 2.5 2.5, 3.5 3.5, 5 5, 6.5 6.5, 8 8, 9 9, 10.5 10.5, 12 12)" -a dp -d 2