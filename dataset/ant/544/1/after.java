class PlaceHold {
  public synchronized boolean setProperty(String name, Object value, boolean verbose) {
    for (Iterator iter = getDelegates(PropertyHelper.PropertySetter.class).iterator();
        iter.hasNext(); ) {
      PropertySetter setter = ((PropertySetter) (iter.next()));
      if (setter.set(name, value, this)) {
        return true;
      }
    }
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
