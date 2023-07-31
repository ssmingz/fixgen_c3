class PlaceHold {
  void columnMouseDoubleClick(Event event) {
    if (isFocusControl() == false) {
      forceFocus();
    }
    if (getIgnoreDoubleClick()) {
      return;
    }
    int itemHeight = getItemHeight();
    TableColumn hitColumn = getColumnAtX(event.x);
    boolean isFullSelection = (getStyle() & SWT.FULL_SELECTION) != 0;
    if (hitColumn != null) {
      int itemIndex = ((event.y - getHeaderHeight()) / itemHeight) + getTopIndex();
      TableItem hitItem = ((TableItem) (getVisibleItem(itemIndex)));
      if ((hitItem != null) && ((hitColumn.getIndex() == TableColumn.FIRST) || isFullSelection)) {
        if (hitItem.isSelectionHit(event.x) == true) {
          Event columnDblClickEvent = new Event();
          columnDblClickEvent.item = hitItem;
          notifyListeners(DefaultSelection, columnDblClickEvent);
        }
      }
    }
  }
}
