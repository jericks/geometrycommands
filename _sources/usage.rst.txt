Usage
=====
View a list of all geom commands::

    geom list

Get help for a command::

    geom buffer --help
    geom buffer: Buffer a geometry by a distance.
    --help                    : Print help message
    -c (--endCapStyle) VAL    : The end cap style (round, flat/butt, square)
    -d (--distance) N         : The buffer distance
    -g (--geometry) VAL       : The input geometry
    -q (--quadrantSegments) N : The number of quadrant segments
    -s (--singleSided)        : The flag for whether the buffer should be single sided

Buffer a geometry::

    echo "POINT (1 1)" | geom buffer -g "POINT(1 1)" -d 10

Or buffer a geometry by piping in text::

    echo "POINT (1 1)" | geom buffer -d 10

Reproject a geometry::

    echo "POINT (1 1)" | geom project -s "EPSG:4326" -t "EPSG:2927"

Generate random points::

    echo "POINT (1 1)" | geom buffer -d 100 | geom random -n 200

Draw a geometry to an image::

    echo "POINT (1 1)" | geom buffer -d 100 | geom draw && open image.png

