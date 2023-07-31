class PlaceHold {
  protected ClassLoader getClassLoaderFromJar(File classjar) throws IOException, TaskException {
    Path lookupPath = new Path();
    lookupPath.setLocation(classjar);
    Path classpath = getCombinedClasspath();
    if (classpath != null) {
      lookupPath.append(classpath);
    }
    final URL[] urls = PathUtil.toURLs(lookupPath);
    return new URLClassLoader(urls);
  }
}
