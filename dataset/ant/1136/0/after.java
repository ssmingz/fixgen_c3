class PlaceHold {
  public Hashtable getUserProperties() {
    synchronized (userProperties) {
      return new Hashtable(userProperties);
    }
  }
}
