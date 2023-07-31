class PlaceHold {
  public void copyUserProperties(Project other) {
    synchronized (userProperties) {
      Enumeration e = userProperties.keys();
      while (e.hasMoreElements()) {
        Object arg = e.nextElement();
        if (inheritedProperties.containsKey(arg)) {
          continue;
        }
        Object value = userProperties.get(arg);
        other.setUserProperty(arg.toString(), value.toString());
      }
    }
  }
}
