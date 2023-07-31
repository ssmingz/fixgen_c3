class PlaceHold {
  void redrawItem(int itemIndex, boolean focusBoundsOnly) {
    if (!items[itemIndex].isInViewport()) {
      return;
    }
    redrawItems(itemIndex, itemIndex, focusBoundsOnly);
  }
}
