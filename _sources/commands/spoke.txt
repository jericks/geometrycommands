spoke
=====

**Name**:

geom spoke

**Description**:

Create a spoke diagram with lines between a single Geometry to other Geometries

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message

   * --web-help : Open help in a web browser



**Example**::

    geom spoke -g "MULTIPOINT ((5.875473869469681 1.0101660098606535), (19.64273518313129 8.032868631563336), (19.397302929472787 10.139284609662209), (12.61792804667091 17.61654337241537), (4.802498121787375 9.17962232316298))" -o "POINT (5 5)"

.. image:: spoke.png