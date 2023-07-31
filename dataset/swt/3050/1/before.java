class PlaceHold {
  public int getSelectionIndex() {
    checkWidget();
    NSTableView widget = ((NSTableView) (view));
    if (widget.numberOfSelectedRows() == 0) {
      return -1;
    }
    NSIndexSet selection = widget.selectedRowIndexes();
    int count = ((int) (selection.count()));
    int[] result = new int[count];
    selection.getIndexes(result, count, 0);
    return ((int) (result[0]));
  }
}
