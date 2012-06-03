subline
=======

**Name**:

geom subline

**Description**:

Extract a sub line from a linear geometry.

**Arguments**:

   * -s startPosition: The start position between 0 and 1

   * -e endPosition: The end position between 0 and 1

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**:::

    geom subline -g "LINESTRING (0 0, 10 10, 20 20)" -s 0.25 -e 0.75