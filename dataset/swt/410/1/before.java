class PlaceHold {
  TableItem _getItem(int index) {
    if (items[index] != null) {
      return items[index];
    }
    return items[index] = new TableItem(this, SWT.NONE, -1, false);
  }
}
