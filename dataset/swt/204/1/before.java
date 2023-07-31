class PlaceHold {
  void redrawSelection(SelectableItem item) {
    int redrawPosition = getVisibleRedrawY(item);
    if (redrawPosition != (-1)) {
      item.redrawSelection(redrawPosition);
    }
  }
}
