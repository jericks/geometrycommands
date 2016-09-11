intersects
==========

**Name**:

geom intersects

**Description**:

Determine if the first geometry intersects the second geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom intersects -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((5 5, 5 15, 15 15, 15 5, 5 5))"