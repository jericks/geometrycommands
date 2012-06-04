random
======

**Name**:

geom random

**Description**:

Generate random points inside the input geometry.

**Arguments**:

   * -n --number: The number of points

   * -r --gridded: The flag for whether the random points should be gridded.

   * -c --constrained: The flag for whether the random points should be constrained to a circle when gridded.

   * -f --gutterFraction: The gutter distance or padding for random points when gridded.

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom random -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -n 100