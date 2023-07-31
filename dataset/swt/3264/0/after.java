class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (menu == null) {
      destroyEmptyMenu(-1);
    } else {
      menu.release(true, false);
      menu = null;
    }
    super.releaseChildren(destroy);
  }
}
