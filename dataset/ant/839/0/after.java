class PlaceHold {
  private URL[] getJarArray(URL[] libJars, URL[] userJars, URL[] systemJars, File toolsJar)
      throws MalformedURLException {
    int numJars = (libJars.length + userJars.length) + systemJars.length;
    if (toolsJar != null) {
      numJars++;
    }
    URL[] jars = new URL[numJars];
    System.arraycopy(libJars, 0, jars, 0, libJars.length);
    System.arraycopy(userJars, 0, jars, libJars.length, userJars.length);
    System.arraycopy(systemJars, 0, jars, userJars.length + libJars.length, systemJars.length);
    if (toolsJar != null) {
      jars[jars.length - 1] = FileUtils.getFileUtils().getFileURL(toolsJar);
    }
    return jars;
  }
}
