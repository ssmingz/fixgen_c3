class PlaceHold {
  void addChild(AccessibleObject child) {
    children.put(new LONG(child.handle), child);
    child.setParent(this);
  }
}
