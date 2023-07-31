class PlaceHold {
  public synchronized void setNewProperty(String name, Object value) {
    for (Iterator iter = getDelegates(PropertyHelper.PropertySetter.class).iterator();
        iter.hasNext(); ) {
      PropertySetter setter = ((PropertySetter) (iter.next()));
      if (setter.setNew(name, value, this)) {
        return;
      }
    }
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
