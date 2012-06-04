draw
====

**Name**:

geom draw

**Description**:

Draw the input geometry to an image file.

**Arguments**:

   * -w --width: The image width

   * -h --height: The image height

   * -f --file: The output File

   * -b --background: The background color

   * -i --backgroundImage: The background image url or file

   * -s --stroke: The stroke Color

   * -l --fill: The fill Color

   * -m --shape: The marker shape (circle, square, ect..)

   * -s --size: The marker size

   * -c --drawCoords: The flag for drawing coordinates or not

   * -e --envelope: The geographical bounds (minx, miny, maxx, maxy)

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom draw -g "POLYGON ((5 5, 5 15, 15 15, 15 5, 5 5))"