class PlaceHold {
  void releaseWidget() {
    if ((menu != null) && (!menu.isDisposed())) {
      menu.releaseResources();
    }
    menu = null;
    super.releaseWidget();
    if (accelerator != 0) {
      removeAccelerator();
    }
    accelerator = 0;
    parent = null;
  }
}
