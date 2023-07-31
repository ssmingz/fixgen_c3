class PlaceHold {
  public void validate() {
    Enumeration e = selectorElements();
    while (e.hasMoreElements()) {
      Object o = e.nextElement();
      if (o instanceof BaseSelector) {
        ((BaseSelector) (o)).validate();
      }
    }
  }
}
