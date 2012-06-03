symdifference
=============

**Name**:

geom symdifference

**Description**:

Calculate the symetric difference between two geometries

**Arguments**:

   * -o --otherGeometry: The other geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom symdifference -g "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))" -o "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))"