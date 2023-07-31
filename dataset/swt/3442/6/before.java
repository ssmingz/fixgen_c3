class PlaceHold {
  public void setSelection(TableTreeItem[] items) {
    checkWidget();
    TableItem[] tableItems = new TableItem[items.length];
    for (int i = 0; i < items.length; i++) {
      if (items[i] == null) {
        throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
      }
      if (!items[i].getVisible()) {
        expandItem(items[i]);
      }
      tableItems[i] = items[i].tableItem;
    }
    table.setSelection(tableItems);
  }
}
