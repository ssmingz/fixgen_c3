class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if (!(((0 <= start) && (start <= end)) && (end < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if ((start == 0) && (end == (itemCount - 1))) {
      removeAll();
    } else {
      int length = (end - start) + 1;
      for (int i = start; i < length; i++) {
        TableItem item = items[i];
        if (item != null) {
          item.release(false);
        }
      }
      int[] selection = getSelectionIndices();
      if (selection.length != 0) {
        int newCount = 0;
        boolean fix = false;
        for (int i = 0; i < selection.length; i++) {
          if ((selection[i] >= start) && (selection[i] <= end)) {
            fix = true;
          } else {
            int newIndex = newCount++;
            selection[newIndex] = selection[i];
            if (selection[newIndex] > end) {
              selection[newIndex] -= length;
              fix = true;
            }
          }
        }
        if (fix) {
          select(selection, newCount, true);
        }
      }
      System.arraycopy(items, start + length, items, start, itemCount - (start + length));
      for (int i = itemCount; i < items.length; i++) {
        items[i] = null;
      }
      itemCount -= length;
      updateRowCount();
    }
    if (itemCount == 0) {
      setTableEmpty();
    }
  }
}
