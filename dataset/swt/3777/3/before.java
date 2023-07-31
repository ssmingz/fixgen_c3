class PlaceHold {
  void releaseWidget() {
    if (menu != null) {
      menu.releaseWidget();
      menu.releaseHandle();
    }
    menu = null;
    super.releaseWidget();
    if (accelerator != 0) {
      parent.destroyAccelGroup();
    }
    accelerator = 0;
    parent = null;
  }
}
