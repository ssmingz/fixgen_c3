class PlaceHold {
  void onDispose(Event event) {
    removeListener(Dispose, listener);
    notifyListeners(Dispose, event);
    event.type = SWT.None;
    inDispose = true;
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
    deallocSelectionHighlightGradientColors();
  }
}
