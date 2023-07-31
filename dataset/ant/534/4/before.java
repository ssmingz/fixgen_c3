class PlaceHold {
  public void addReference(String referenceName, Object value) {
    synchronized (references) {
      Object old = ((AntRefTable) (references)).getReal(referenceName);
      if (old == value) {
        return;
      }
      if ((old != null) && (!(old instanceof UnknownElement))) {
        log("Overriding previous definition of reference to " + referenceName, MSG_WARN);
      }
      log("Adding reference: " + referenceName, MSG_DEBUG);
      references.put(referenceName, value);
    }
  }
}
