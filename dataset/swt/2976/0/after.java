class PlaceHold {
  int processActivate(int columnIndex, int arg1, int int2) {
    if (fakeColumn != 0) {
      return 0;
    }
    TableColumn column = columns[columnIndex];
    if (column != null) {
      column.postEvent(Selection);
    }
    return 0;
  }
}
