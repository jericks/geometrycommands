reflect
=======

**Name**:

geom reflect

**Description**:

Create a new geometry by applying the reflect affine transformation on the input geometry.

**Arguments**:

   * -0 --x0: The x-ordinate of a point on the reflection line

   * -1 --y0: The y-ordinate of a point on the reflection line

   * -2 --x1: The x-ordinate of a another point on the reflection line

   * -3 --y1: The y-ordinate of a another point on the reflection line

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom reflect -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -0 5 -1 2