placepoint
==========

**Name**:

geom placepoint

**Description**:

Place a point on the input linear geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom placepoint -g "LINESTRING (0 0, 5 5, 10 10)" -o "POINT (3 4.5)"