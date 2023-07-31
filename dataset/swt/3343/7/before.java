class PlaceHold {
  void releaseCildren(boolean destroy) {
    if (caret != null) {
      caret.releaseChildren(false);
      caret = null;
    }
    super.releaseChildren(destroy);
  }
}
