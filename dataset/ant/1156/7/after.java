class PlaceHold {
  protected ClassLoader getClassLoaderForBuild() throws TaskException {
    if (classpathLoader != null) {
      return classpathLoader;
    }
    Path combinedClasspath = getCombinedClasspath();
    if (combinedClasspath == null) {
      classpathLoader = getClass().getClassLoader();
    } else {
      final URL[] urls = PathUtil.toURLs(combinedClasspath);
      classpathLoader = new URLClassLoader(urls);
    }
    return classpathLoader;
  }
}
