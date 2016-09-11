split
=====

**Name**:

geom split

**Description**:

Split a Geometry by another Geometry

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom split -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "LINESTRING (0 0, 10 10)"