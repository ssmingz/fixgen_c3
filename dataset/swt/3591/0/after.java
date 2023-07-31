class PlaceHold {
  void releaseWidget() {
    if (menu != null) {
      menu.releaseResources();
    }
    menu = null;
    super.releaseWidget();
    if (accelerator != 0) {
      parent.destroyAccelerators();
    }
    accelerator = 0;
    display.removeMenuItem(this);
    parent = null;
  }
}
