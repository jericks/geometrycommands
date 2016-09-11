relate
======

**Name**:

geom relate

**Description**:

Determine if the input Geometry and the other Geometry are related according to the DE-9IM intersection matrix or calculate the DE-9IM.

**Arguments**:

   * -m --matrix: The DE-9IM intersection matrix

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom relate -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POINT (5 5)"