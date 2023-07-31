class PlaceHold {
  public synchronized boolean setProperty(String name, Object value, boolean verbose) {
    if (null != userProperties.get(name)) {
      if (verbose) {
        project.log(("Override ignored for user property \"" + name) + "\"", MSG_VERBOSE);
      }
      return false;
    }
    if ((null != properties.get(name)) && verbose) {
      project.log(("Overriding previous definition of property \"" + name) + "\"", MSG_VERBOSE);
    }
    if (verbose) {
      project.log((("Setting project property: " + name) + " -> ") + value, MSG_DEBUG);
    }
    if ((name != null) && (value != null)) {
      properties.put(name, value);
    }
    return true;
  }
}
