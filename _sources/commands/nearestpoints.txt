nearestpoints
============

**Name**:

geom nearestpoints

**Description**:

Find the nearest Points between this Geometry and another Geometry.

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

**Example**::

    geom nearestpoints -g "POLYGON ((90 90, 90 110, 110 110, 110 90, 90 90))" -o "POLYGON ((173.96210441769105 -94.53669248798772, 193.14058991095382 -88.86344877872318, 198.81383362021836 -108.04193427198595, 179.6353481269556 -113.71517798125049, 173.96210441769105 -94.53669248798772))"
