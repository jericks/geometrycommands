reduceprecision
===============

**Name**:

geom reduceprecision

**Description**:

Reduce the precision of the input geometry.

**Arguments**:

   * -t --type: The precision model type (FIXED, FLOATING, FLOATING_SINGLE)

   * -s --scale: The precision model scale when type is FLOATING

   * -p --pointWise: Whether the precision reducer operates pointwise

   * -r --removeCollapsed: Whether the precision reducer should remove collapsed geometry

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom reduceprecision -g "POINT (-5.70068359375 45.1416015625)" -s 2 -t fixed