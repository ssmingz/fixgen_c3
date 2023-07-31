class PlaceHold {
  public void execute() throws BuildException {
    if (((cls == null) && (classes.size() == 0)) && (files.size() == 0)) {
      throw new BuildException("class attribute must be set!", getLocation());
    }
    if (((cls != null) && (classes.size() > 0)) && (files.size() > 0)) {
      throw new BuildException(
          "set class attribute OR class element OR fileset, " + "not 2 or more of them.",
          getLocation());
    }
    if (destDir != null) {
      if (!destDir.isDirectory()) {
        throw new BuildException(
            ("destination directory \"" + destDir) + "\" does not exist or is not a directory",
            getLocation());
      }
      if (outputFile != null) {
        throw new BuildException(
            "destdir and outputFile are mutually " + "exclusive", getLocation());
      }
    }
    if (classpath == null) {
      classpath = new Path(getProject()).concatSystemClasspath("last");
    } else {
      classpath = classpath.concatSystemClasspath("ignore");
    }
    JavahAdapter ad =
        JavahAdapterFactory.getAdapter(
            facade.getImplementation(), this, createImplementationClasspath());
    if (!ad.compile(this)) {
      throw new BuildException("compilation failed");
    }
  }
}
