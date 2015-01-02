Usage
=====
View a list of all geom commands::

    geom list

Buffer a geometry::

    echo "POINT (1 1)" | geom buffer -d 10

Reproject a geometry::

    echo "POINT (1 1)" | geom project -s "EPSG:4326" -t "EPSG:2927"

Generate random points::

    echo "POINT (1 1)" | geom buffer -d 100 | geom random -n 200

Draw a geometry to an image::

    echo "POINT (1 1)" | geom buffer -d 100 | geom draw && open image.png

