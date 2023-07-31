class PlaceHold {
  void releaseChildren(boolean destroy) {
    Control[] children = _getChildren();
    for (int i = 0; i < children.length; i++) {
      Control child = children[i];
      if ((child != null) && (!child.isDisposed())) {
        child.releaseChildren(false);
      }
    }
    super.releaseChildren(destroy);
  }
}
