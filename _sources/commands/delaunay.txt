delaunay
========

**Name**:

geom delaunay

**Description**:

Generate a delaunay triangulation of the input geometry.

**Arguments**:

   * -c --conforming: The flag for whether to use conforming algorithm

   * -g --geometry: The input geometry

   * --help : Print help message



**Example**::

    geom delaunay -g "POINT (1 1)" -d 100 | geom random -n 100 | geom delaunay
    
.. image:: images/delaunay.png
    :alt: geom delaunay example
