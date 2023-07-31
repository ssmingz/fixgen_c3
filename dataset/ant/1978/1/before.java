class PlaceHold {
  protected ClassLoader getClassLoaderForBuild() throws TaskException {
    if (classpathLoader != null) {
      return classpathLoader;
    }
    Path combinedClasspath = getCombinedClasspath();
    if (combinedClasspath == null) {
      classpathLoader = getClass().getClassLoader();
    } else {
      classpathLoader = new AntClassLoader(getTask().getProject(), combinedClasspath);
    }
    return classpathLoader;
  }
}
