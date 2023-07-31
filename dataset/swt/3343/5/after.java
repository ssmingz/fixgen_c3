class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (menu != null) {
      menu.release(false);
      menu = null;
    }
    super.releaseChildren(destroy);
  }
}
