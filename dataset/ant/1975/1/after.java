class PlaceHold {
  protected Path getCompileClasspath() {
    Path classpath = new Path(attributes.getProject());
    classpath.setLocation(attributes.getBase());
    if (attributes.getClasspath() == null) {
      if (attributes.getIncludeantruntime()) {
        classpath.addExisting(systemClasspath);
      }
    } else if (attributes.getIncludeantruntime()) {
      classpath.addExisting(attributes.getClasspath().concatSystemClasspath("last"));
    } else {
      classpath.addExisting(attributes.getClasspath().concatSystemClasspath("ignore"));
    }
    if (attributes.getIncludejavaruntime()) {
      classpath.addJavaRuntime();
    }
    return classpath;
  }
}
