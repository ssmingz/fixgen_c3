class PlaceHold {
  public void clear(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemsCount))) {
      error(ERROR_INVALID_RANGE);
    }
    Rectangle bounds = items[index].getBounds();
    int oldRightX = bounds.x + bounds.width;
    items[index].clear();
    if (columns.length == 0) {
      updateHorizontalBar(0, -oldRightX);
    }
    redrawItem(index, false);
  }
}
