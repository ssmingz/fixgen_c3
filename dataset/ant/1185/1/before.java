class PlaceHold {
  protected Path getCompileClasspath() {
    Path classpath = new Path(project);
    if ((destDir != null) && getJavac().isIncludeDestClasses()) {
      classpath.setLocation(destDir);
    }
    Path cp = compileClasspath;
    if (cp == null) {
      cp = new Path(project);
    }
    if (includeAntRuntime) {
      classpath.addExisting(cp.concatSystemClasspath("last"));
    } else {
      classpath.addExisting(cp.concatSystemClasspath("ignore"));
    }
    if (includeJavaRuntime) {
      classpath.addJavaRuntime();
    }
    return classpath;
  }
}
