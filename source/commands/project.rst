project
=======

**Name**:

geom project

**Description**:

Project the input geometry from one coordinate system to another.

**Arguments**:

   * -s --source: The source projection

   * -t --target: The target projection

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom project -g "POINT (1179931.55 645310.31)" -s EPSG:2927 -t EPSG:4326