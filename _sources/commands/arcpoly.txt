arcpoly
=======

**Name**:

geom arcpoly

**Description**:

Creates an arc polygon from a start angle and an angle extent.

**Arguments**:

   * -a --startAngle: The start angle (in radians)

   * -e --angleExtent: The size of angle (in radians)

   * -d --degrees: The flag for whether given angle measures are in degrees (true) or radians (false)

   * -w --width: The width

   * -h --height: The height

   * -p --numberOfPoints: The number of points

   * -r --rotation: The rotation

   * -c --center: The flag to use center (true) or the base (false)

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom arcpoly -g "POINT (100 100)" -a 45 -e 90 -d -p 20 -w 500 -h 500

.. image:: arcpoly.png