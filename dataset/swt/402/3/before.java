class PlaceHold {
  void outlineView_setObjectValue_forTableColumn_byItem(
      int id, int sel, int outlineView, int object, int tableColumn, int itemID) {
    if ((checkColumn != null) && (tableColumn == checkColumn.id)) {
      TreeItem item = ((TreeItem) (display.getWidget(itemID)));
      item.checked = !item.checked;
      Event event = new Event();
      event.detail = SWT.CHECK;
      event.item = item;
      postEvent(Selection, event);
      item.redraw(-1);
    }
  }
}
