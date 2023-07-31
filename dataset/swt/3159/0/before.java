class PlaceHold {
  void releaseWidget() {
    if (menuBar != null) {
      menuBar.releaseResources();
    }
    menuBar = null;
    if (menus != null) {
      for (int i = 0; i < menus.length; i++) {
        Menu menu = menus[i];
        if ((menu != null) && (!menu.isDisposed())) {
          menu.dispose();
        }
      }
    }
    menus = null;
    super.releaseWidget();
    image = null;
    images = null;
  }
}
