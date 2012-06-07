.. Geometry Commands documentation master file, created by
   sphinx-quickstart on Mon May  7 19:12:31 2012.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Geometry Commands
=================

Geometry Commands is a command line application for processing geometry. It is inspired by the wonderful Java Topology Suite (JTS) library and the Unix Philosophy. 

It contains one command line application (geom) with numerous subcommands (buffer, centroid, envelope) that generally read WKT geometry from standard input and then write WKT geometry to standard output.  This enables several geometry commands to be chained otogether using pipes.::

    echo "POINT (1 1)" | geom buffer -d 10 | geom envelope

Geometry Commands is open source under the MIT license.  I hope you find it useful.  The code is available at `github <https://github.com/jericks/geometrycommands>`_. If you find any bugs or would like any enhancements please use the GitHub `issue tracker <https://github.com/jericks/geometrycommands/issues>`_.

To install, simple download the latest `zip file <https://github.com/jericks/geometrycommands/downloads>`_ and place the **bin** directory in your path. You should then be able to run the **geom** command.

Usage
-----

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

Command Documentation
---------------------

.. toctree::
   :maxdepth: 2

   commands.rst


Indices and tables
==================

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`

