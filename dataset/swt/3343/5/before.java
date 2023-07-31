class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (menu != null) {
      menu.releaseChildren(false);
      menu = null;
    }
    super.releaseChildren(destroy);
  }
}
