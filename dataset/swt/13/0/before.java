class PlaceHold {
  public TableColumn getColumn(int index) {
    checkWidget();
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if ((count == 1) && (columns[0] == null)) {
      count = 0;
    }
    if (!((0 <= index) && (index < count))) {
      error(ERROR_INVALID_RANGE);
    }
    return columns[index];
  }
}
