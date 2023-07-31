class PlaceHold {
  void releaseWidget() {
    if ((menu != null) && (!menu.isDisposed())) {
      menu.releaseResources();
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
