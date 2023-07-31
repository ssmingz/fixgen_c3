class PlaceHold {
  public void showItem(TableTreeItem item) {
    checkWidget();
    if (item == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (!item.getVisible()) {
      expandItem(item);
    }
    TableItem tableItem = item.tableItem;
    table.showItem(tableItem);
  }
}
