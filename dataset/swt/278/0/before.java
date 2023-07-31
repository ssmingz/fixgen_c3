class PlaceHold {
  void destroyItem(CoolItem item) {
    if (inDispose) {
      return;
    }
    int row = findItem(item).y;
    if (row == (-1)) {
      return;
    }
    Rectangle bounds = item.getBounds();
    removeItemFromRow(item, row, true);
    int index = 0;
    while (index < originalItems.length) {
      if (originalItems[index] == item) {
        break;
      }
      index++;
    }
    int length = originalItems.length - 1;
    CoolItem[] newOriginals = new CoolItem[length];
    System.arraycopy(originalItems, 0, newOriginals, 0, index);
    System.arraycopy(originalItems, index + 1, newOriginals, index, length - index);
    originalItems = newOriginals;
    redraw(bounds.x, bounds.y, MINIMUM_WIDTH, bounds.height, false);
    relayout();
  }
}
