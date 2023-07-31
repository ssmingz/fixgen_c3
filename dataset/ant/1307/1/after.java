class PlaceHold {
  protected ClassLoader getClassLoaderFromJar(File classjar) throws IOException, TaskException {
    Path lookupPath = new Path();
    lookupPath.setLocation(classjar);
    Path classpath = getCombinedClasspath();
    if (classpath != null) {
      lookupPath.append(classpath);
    }
    return new URLClassLoader(lookupPath.toURLs());
  }
}
