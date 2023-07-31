class PlaceHold {
  public synchronized void setNewProperty(String ns, String name, Object value) {
    if (null != properties.get(name)) {
      project.log("Override ignored for property " + name, MSG_VERBOSE);
      return;
    }
    boolean done = setPropertyHook(ns, name, value, false, false, true);
    if (done) {
      return;
    }
    project.log((("Setting project property: " + name) + " -> ") + value, MSG_DEBUG);
    if ((name != null) && (value != null)) {
      properties.put(name, value);
    }
  }
}
