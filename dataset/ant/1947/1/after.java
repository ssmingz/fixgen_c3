class PlaceHold {
  public synchronized void setUserProperty(String ns, String name, Object value) {
    project.log((("Setting ro project property: " + name) + " -> ") + value, MSG_DEBUG);
    userProperties.put(name, value);
    boolean done = setPropertyHook(ns, name, value, false, true, false);
    if (done) {
      return;
    }
    properties.put(name, value);
  }
}
