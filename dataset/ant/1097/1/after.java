class PlaceHold {
  public void validate() {
    if (isReference()) {
      ((AbstractSelectorContainer) (getCheckedRef())).validate();
    }
    dieOnCircularReference();
    Enumeration e = selectorElements();
    while (e.hasMoreElements()) {
      Object o = e.nextElement();
      if (o instanceof BaseSelector) {
        ((BaseSelector) (o)).validate();
      }
    }
  }
}
