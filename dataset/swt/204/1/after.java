class PlaceHold {
  void redrawSelection(SelectableItem item) {
    if (item.isDisposed()) {
      return;
    }
    int redrawPosition = getVisibleRedrawY(item);
    if (redrawPosition != (-1)) {
      item.redrawSelection(redrawPosition);
    }
  }
}
