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
    checkButton.setSelection((tree1.getStyle() & SWT.CHECK) != 0);
    checkButton.setSelection((tree2.getStyle() & SWT.CHECK) != 0);
    fullSelectionButton.setSelection((tree1.getStyle() & SWT.FULL_SELECTION) != 0);
    fullSelectionButton.setSelection((tree2.getStyle() & SWT.FULL_SELECTION) != 0);
    try {
      TreeColumn column = tree1.getColumn(0);
      moveableColumns.setSelection(column.getMoveable());
      resizableColumns.setSelection(column.getResizable());
    } catch (IllegalArgumentException ex) {
    }
    headerVisibleButton.setSelection(tree1.getHeaderVisible());
    linesVisibleButton.setSelection(tree1.getLinesVisible());
  }
}
