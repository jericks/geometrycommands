draw
====

**Name**:

geom draw

**Description**:

Draw the input geometry to an image file.

**Arguments**:

   * -f --file: The output File

   * -w --width: The image width

   * -h --height: The image height

   * -b --background: The background color

   * -i --backgroundImage: The background image url or file

   * -s --stroke: The stroke Color

   * -t --strokeOpacity: The stroke opacity

   * -r --strokeWidth: The stroke width

   * -l --fill: The fill Color

   * -o --fillOpacity: The fill opacity

   * -m --shape: The marker shape (circle, square, ect..)

   * -z --size: The marker size

   * -c --drawCoords: The flag for drawing coordinates or not

   * -e --envelope: The geographical bounds (minx, miny, maxx, maxy)

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom draw -g "POLYGON ((5 5, 5 15, 15 15, 15 5, 5 5))"