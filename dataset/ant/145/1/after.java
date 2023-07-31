class PlaceHold {
  protected Vector getOptions() {
    Vector options = new Vector();
    if (verbose) {
      options.addElement("-verbose");
    }
    if (debugscanner) {
      options.addElement("-ds");
    }
    if (debugparser) {
      options.addElement("-dp");
    }
    if (classPath != null) {
      options.addElement("-classpath");
      options.addElement(classPath.toString());
    }
    if (sourcePath != null) {
      options.addElement("-sourcepath");
      options.addElement(sourcePath.toString());
    }
    options.addElement(targetFile.getAbsolutePath());
    return options;
  }
}
