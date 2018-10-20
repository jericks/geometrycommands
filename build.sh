$GRAALVM_HOME/bin/native-image --verbose \
  -jar target/geom-0.6-SNAPSHOT.jar \
  -H:+ReportUnsupportedElementsAtRuntime \
  -H:ReflectionConfigurationFiles=src/config/config_geom.json \
  -H:ReflectionConfigurationFiles=src/config/config_args4j.json \
  -H:IncludeResources=META-INF/services/*.* \
  -H:IncludeResourceBundles=org.kohsuke.args4j.Messages \
  -H:IncludeResourceBundles=org.kohsuke.args4j.spi.Messages \
  -H:+JNI \
  -H:Name=geom 