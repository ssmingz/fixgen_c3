class PlaceHold {
  int processActivate(int columnIndex, int arg1, int int2) {
    TableColumn column = columns[columnIndex];
    if (column != null) {
      column.postEvent(Selection);
    }
    return 0;
  }
}
