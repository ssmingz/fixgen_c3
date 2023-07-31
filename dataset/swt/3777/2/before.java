class PlaceHold {
  void releaseWidget() {
    if ((menu != null) && (!menu.isDisposed())) {
      menu.releaseWidget();
      menu.releaseHandle();
    }
    menu = null;
    super.releaseWidget();
    accelerator = 0;
    if (this == parent.defaultItem) {
      parent.defaultItem = null;
    }
    parent = null;
  }
}
