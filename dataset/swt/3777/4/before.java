class PlaceHold {
  void releaseWidget() {
    if (menu != null) {
      menu.releaseWidget();
      menu.releaseHandle();
    }
    menu = null;
    super.releaseWidget();
    if (accelerator != 0) {
      parent.destroyAcceleratorTable();
    }
    accelerator = 0;
    Decorations shell = parent.parent;
    shell.remove(this);
    parent = null;
  }
}
