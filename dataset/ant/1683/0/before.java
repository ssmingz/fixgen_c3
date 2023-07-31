class PlaceHold {
  private Class findClassInComponents(String name) throws ClassNotFoundException {
    InputStream stream = null;
    String classFilename = getClassFilename(name);
    try {
      Enumeration e = pathComponents.elements();
      while (e.hasMoreElements()) {
        File pathComponent = ((File) (e.nextElement()));
        try {
          stream = getResourceStream(pathComponent, classFilename);
          if (stream != null) {
            log((("Loaded from " + pathComponent) + " ") + classFilename, MSG_DEBUG);
            return getClassFromStream(stream, name, pathComponent);
          }
        } catch (SecurityException se) {
          throw se;
        } catch (IOException ioe) {
          log(
              ((("Exception reading component " + pathComponent) + " (reason: ") + ioe.getMessage())
                  + ")",
              MSG_VERBOSE);
        }
      }
      throw new ClassNotFoundException(name);
    } finally {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
      }
    }
  }
}
