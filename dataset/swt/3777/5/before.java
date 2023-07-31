class PlaceHold {
  void releaseWidget() {
    if (menus != null) {
      for (int i = 0; i < menus.length; i++) {
        Menu menu = menus[i];
        if ((menu != null) && (!menu.isDisposed())) {
          menu.releaseWidget();
          menu.releaseHandle();
        }
      }
    }
    menuBar = null;
    menus = null;
    image = null;
    super.releaseWidget();
    defaultButton = saveDefault = null;
    text = null;
  }
}
