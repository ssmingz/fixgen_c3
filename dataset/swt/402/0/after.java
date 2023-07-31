class PlaceHold {
  void tableView_setObjectValue_forTableColumn_row(
      int id, int sel, int aTableView, int anObject, int aTableColumn, int rowIndex) {
    if ((checkColumn != null) && (aTableColumn == checkColumn.id)) {
      TableItem item = items[((int) (rowIndex))];
      item.checked = !item.checked;
      Event event = new Event();
      event.detail = SWT.CHECK;
      event.item = item;
      event.index = ((int) (rowIndex));
      sendSelectionEvent(Selection, event, false);
      item.redraw(-1);
    }
  }
}
