translate
=========

**Name**:

geom translate

**Description**:

Create a new geometry by applying the translate affine transformation on the input geometry.

**Arguments**:

   * -x --xDistance: The value to translate by in the x direction

   * -y --yDistance: The value to translate by in the y direction

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom translate -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -x 4 -y 2