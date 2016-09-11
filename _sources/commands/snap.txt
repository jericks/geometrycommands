snap
====

**Name**:

geom snap

**Description**:

Snap the input geometry to the other geometry.

**Arguments**:

   * -d --distance: The distance/tolerance

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom snap -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11))" -d 1.5