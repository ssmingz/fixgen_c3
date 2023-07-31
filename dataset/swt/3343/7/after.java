class PlaceHold {
  void releaseCildren(boolean destroy) {
    if (caret != null) {
      caret.release(false);
      caret = null;
    }
    super.releaseChildren(destroy);
  }
}
