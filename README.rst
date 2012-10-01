Geometry command line library.
==============================
Geometry commands is a command line library for processing geometry that follows the unix philosophy. Each command does one thing well (buffer, centroid, envelope) by reading in a geometry, processing the geometry, and writing the geometry out as WKT.  Individual commands can be connected with unix pipes.  For more information please visit the `web site <http://jericks.github.com/geometrycommands/index.html>`_.

Libraries
---------
JTS: 
    http://tsusiatsoftware.net/jts/javadoc/com/vividsolutions/jts/geom/Geometry.html
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

Build
-----
Geometry Commands depends on the Java Topology Suite (JTS) and Proj4j and uses Maven as a build tool.
Unfortunately, Proj4j is not in a Maven repository.  So, to build Geometry Commands you must first
checkout Proj4j, build the jar, and install it in your local Maven repository.

Checkout source code:: 

    svn checkout http://svn.osgeo.org/metacrs/proj4j/trunk/ proj4j

Build it:: 
    
    cd proj4j/src
    ant

Install it::

    cd proj4j/
    mvn install:install-file -Dfile=build/distro/lib/proj4j-0.1.0.jar -DgroupId=org.osgeo -DartifactId=proj4j -Dversion=0.1.0 -Dpackaging=jar

Now you can build Geometry Commands using Maven:: 

    cd /geometrycommands
    mvn clean install

License
-------
Geometry Commands is open source and licensed under the MIT License.
