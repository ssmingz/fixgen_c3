class PlaceHold {
  void setExampleWidgetState() {
    setItemBackground();
    setItemForeground();
    setItemFont();
    setCellBackground();
    setCellForeground();
    setCellFont();
    if (!instance.startup) {
      setColumnsMoveable();
      setColumnsResizable();
      setWidgetHeaderVisible();
      setWidgetSortIndicator();
      setWidgetLinesVisible();
    }
    super.setExampleWidgetState();
    noScrollButton.setSelection((table1.getStyle() & SWT.NO_SCROLL) != 0);
    checkButton.setSelection((table1.getStyle() & SWT.CHECK) != 0);
    fullSelectionButton.setSelection((table1.getStyle() & SWT.FULL_SELECTION) != 0);
    hideSelectionButton.setSelection((table1.getStyle() & SWT.HIDE_SELECTION) != 0);
    try {
      TableColumn column = table1.getColumn(0);
      moveableColumns.setSelection(column.getMoveable());
      resizableColumns.setSelection(column.getResizable());
    } catch (IllegalArgumentException ex) {
    }
    headerVisibleButton.setSelection(table1.getHeaderVisible());
    linesVisibleButton.setSelection(table1.getLinesVisible());
  }
}
