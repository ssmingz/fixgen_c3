class PlaceHold {
  private void createClassLoader() {
    Path userClasspath = commandline.getClasspath();
    if (userClasspath != null) {
      if (reloading || (classLoader == null)) {
        Path classpath = ((Path) (userClasspath.clone()));
        if (includeAntRuntime) {
          log(("Implicitly adding " + antRuntimeClasses) + " to CLASSPATH", MSG_VERBOSE);
          classpath.append(antRuntimeClasses);
        }
        classLoader = getProject().createClassLoader(classpath);
        log("Using CLASSPATH " + classLoader.getClasspath(), MSG_VERBOSE);
        classLoader.setParentFirst(false);
        classLoader.addJavaLibraries();
        log("Using CLASSPATH " + classLoader.getClasspath(), MSG_VERBOSE);
        classLoader.addSystemPackageRoot("junit");
        classLoader.addSystemPackageRoot("org.apache.tools.ant");
      }
    }
  }
}
