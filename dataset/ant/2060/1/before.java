class PlaceHold {
  public void copyInheritedProperties(Project other) {
    synchronized (inheritedProperties) {
      Enumeration e = inheritedProperties.keys();
      while (e.hasMoreElements()) {
        String arg = e.nextElement().toString();
        if (other.getUserProperty(arg) != null) {
          continue;
        }
        Object value = inheritedProperties.get(arg);
        other.setInheritedProperty(arg, value.toString());
      }
    }
  }
}
