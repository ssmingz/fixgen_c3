class PlaceHold {
  boolean traversePage(boolean next) {
    int count = getItemCount();
    if (count <= 1) {
      return false;
    }
    int index = getSelectionIndex();
    if (index == (-1)) {
      index = 0;
    } else {
      int offset = (next) ? 1 : -1;
      index = ((index + offset) + count) % count;
    }
    setSelection(index, true);
    return index == getSelectionIndex();
  }
}
