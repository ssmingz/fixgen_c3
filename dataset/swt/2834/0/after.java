class PlaceHold {
  void onDispose() {
    inDispose = true;
    hideToolTip();
    if ((showMenu != null) && (!showMenu.isDisposed())) {
      showMenu.dispose();
      showMenu = null;
    }
    int length = items.length;
    for (int i = 0; i < length; i++) {
      if (items[i] != null) {
        items[i].dispose();
      }
    }
    selectionGradientColors = null;
    selectionGradientPercents = null;
    selectionBgImage = null;
    selectionBackground = null;
    selectionForeground = null;
  }
}
