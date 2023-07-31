class PlaceHold {
  int getX() {
    int index = getIndex();
    if (index == 0) {
      return -parent.horizontalOffset;
    }
    TreeColumn previousColumn = parent.getColumns()[index - 1];
    return previousColumn.getX() + previousColumn.width;
  }
}
