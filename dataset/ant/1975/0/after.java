class PlaceHold {
  protected Path getCompileClasspath() {
    Path classpath = new Path(project);
    if (destDir != null) {
      classpath.setLocation(destDir);
    }
    if (compileClasspath == null) {
      if (includeAntRuntime) {
        classpath.addExisting(systemClasspath);
      }
    } else if (includeAntRuntime) {
      classpath.addExisting(compileClasspath.concatSystemClasspath("last"));
    } else {
      classpath.addExisting(compileClasspath.concatSystemClasspath("ignore"));
    }
    if (includeJavaRuntime) {
      classpath.addJavaRuntime();
    }
    return classpath;
  }
}
