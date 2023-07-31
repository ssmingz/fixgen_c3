class PlaceHold {
  public synchronized void setNewProperty(String name, Object value) {
    if (null != properties.get(name)) {
      project.log(("Override ignored for property \"" + name) + "\"", MSG_VERBOSE);
      return;
    }
    project.log((("Setting project property: " + name) + " -> ") + value, MSG_DEBUG);
    if ((name != null) && (value != null)) {
      properties.put(name, value);
    }
  }
}
