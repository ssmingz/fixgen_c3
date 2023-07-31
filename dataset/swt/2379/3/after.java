class PlaceHold {
  void remove(int index, boolean fixScroll) {
    if (index != (itemCount - 1)) {
      fixSelection(index, false);
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    updateRowCount();
    if (fixScroll) {
      setScrollWidth();
    }
  }
}
