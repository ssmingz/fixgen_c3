class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (menu == null) {
      destroyEmptyMenu(-1);
    } else {
      menu.releaseChildren(true, false);
      menu = null;
    }
    super.releaseChildren(destroy);
  }
}
