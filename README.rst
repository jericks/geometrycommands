.. image:: https://github.com/jericks/geometrycommands/workflows/Maven%20Build/badge.svg
    :target: https://github.com/jericks/geometrycommands/actions

Geometry command line library.
==============================
Geometry commands is a command line library for processing geometry that follows the unix philosophy. Each command does one thing well (buffer, centroid, envelope) by reading in a geometry, processing the geometry, and writing the geometry out as WKT.  Individual commands can be connected with unix pipes.  For more information please visit the `web site <http://jericks.github.com/geometrycommands/index.html>`_.

Libraries
---------
JTS: 
    https://locationtech.github.io/jts/
Proj4j: 
    http://trac.osgeo.org/proj4j/
Args4J: 
    http://args4j.kohsuke.org/

Examples:
---------
Geometry input with -g argument::

    >>> geom buffer -g "POINT (10 10)" -d 2

Geometry input using standard input stream::

    >>> echo "POINT (10 10)" | geom buffer -d 20

Piping results of one geometry command to another::

    >>> geom buffer -g "POINT (10 10)" -d 2 | geom envelope

Determine if one geometry contains another::

    >>> echo "POINT (0 0)" | geom buffer -d 10 | geom contains -o "POINT (5 5)"
    true
    >>> echo "POINT (0 0)" | geom buffer -d 10 | geom contains -o "POINT (25 25)"
    false

Buffer a point, get coordinates, draw coordinates to image, and open the image::

    >>> echo "POINT (10 10)" | geom buffer -d 5 | geom coordinates | geom draw && open image.png

List available geometry commands::

    >>> geom list
    list
    buffer
    centroid
    contains
    convexHull
    draw
    difference
    envelope
    intersection

Getting Help
------------
Each command contains a --help option::

    >>> geom buffer --help
    geom buffer: Buffer a geometry by a distance.
    --help                    : Print help message
    -c (--endCapStyle) VAL    : The end cap style (round, flat/butt, square)
    -d (--distance) N         : The buffer distance
    -g (--geometry) VAL       : The input geometry
    -q (--quadrantSegments) N : The number of quadrant segments
    -s (--singleSided)        : The flag for whether the buffer should be single sided

There is a man page for each subcommand::

    >>> man geom-buffer
    geom-buffer(1)                                                  geom-buffer(1)

    NAME
           geom buffer

    DESCRIPTION
           Buffer a geometry by a distance.

    USAGE
           geom buffer -g "POINT (1 1)" -d 10

Finally, there is a bash completion script which makes using geom with bash much easier.

Install it in your .bash_profile::

    source /Users/you/geom/shell/geom_bash_comp

Build
-----
Geometry Commands depends on the Java Topology Suite (JTS) and Proj4j and uses Maven as a build tool.::
    
    git checkout https://github.com/jericks/geometrycommands.git
    cd /geometrycommands
    mvn clean install

Build Native App
----------------
You can build a native app using Graalvm for MacOS or Linux.  Download `Graalvm <http://www.graalvm.org/downloads/>`_ and export a GRAALVM_HOME variable::

     export GRAALVM_HOME=/Users/you/Applications/graalvm-ce-21.3.0/Contents/Home

Then you can run::

    ./mvnw clean install -DskipTests -Pnative

This will create a native geom executable.

Presentations
-------------

`geometry commands <http://www.slideshare.net/JaredErickson/geometry-commands>`_

License
-------
Geometry Commands is open source and licensed under the MIT License.
