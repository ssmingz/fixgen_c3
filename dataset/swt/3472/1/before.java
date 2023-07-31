class PlaceHold {
  void release() {
    if (children != null) {
      for (int i = 0; i < children.size(); i++) {
        Accessible child = children.elementAt(i);
        child.dispose();
      }
    }
    if (accessibleObject != null) {
      accessibleObject.release();
      accessibleObject = null;
    }
  }
}
