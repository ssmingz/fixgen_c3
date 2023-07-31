class PlaceHold {
  public Hashtable getProperties() {
    synchronized (properties) {
      return new Hashtable(properties);
    }
  }
}
