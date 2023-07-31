class PlaceHold {
  void redrawItem(int itemIndex, boolean focusBoundsOnly) {
    if (!availableItems[itemIndex].isInViewport()) {
      return;
    }
    redrawItems(itemIndex, itemIndex, focusBoundsOnly);
  }
}
