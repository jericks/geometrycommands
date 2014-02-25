slice
=====

**Name**:

geom slice

**Description**:

Get a subset of geometries from a geometrycollection.

**Arguments**:

   * -s --start The start index 
     
   * -e --end The end index 

   * -g --geometry: The input geometry

   * --help : Print help message

**Example**::

    geom slice -g "MULTIPOINT ((1 1), (2 2), (3 3), (4 4), (5 5))" -s 1 -e 3
