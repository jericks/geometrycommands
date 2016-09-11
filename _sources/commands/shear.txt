shear
=====

**Name**:

geom shear

**Description**:

Create a new geometry by apply a shear affine transformation to the input geometry

**Arguments**:

   * -x --xDistance: The value to translate by in the x direction

   * -y -yDistance: The value to translate by in the y direction

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom shear -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -x 4 -y 2