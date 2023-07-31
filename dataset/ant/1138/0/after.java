class PlaceHold {
  private void addExistingToClasspath(StringBuffer target, String source) {
    StringTokenizer tok = new StringTokenizer(source, System.getProperty("path.separator"), false);
    while (tok.hasMoreTokens()) {
      File f = project.resolveFile(tok.nextToken());
      if (f.exists()) {
        target.append(File.pathSeparator);
        target.append(f.getAbsolutePath());
      } else {
        log("Dropping from classpath: " + f.getAbsolutePath(), MSG_VERBOSE);
      }
    }
  }
}
