class PlaceHold {
  void addChild(AccessibleObject child) {
    children.put(new Integer(child.handle), child);
    child.setParent(this);
  }
}
