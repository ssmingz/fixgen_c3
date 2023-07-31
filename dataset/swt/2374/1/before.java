class PlaceHold {
  void setExampleWidgetState() {
    setColumnsMoveable();
    setColumnsResizable();
    setItemBackground();
    setItemForeground();
    setItemFont();
    setCellBackground();
    setCellForeground();
    setCellFont();
    if (!instance.startup) {
      setWidgetHeaderVisible();
      setWidgetSortIndicator();
      setWidgetLinesVisible();
    }
    super.setExampleWidgetState();
    checkButton.setSelection((table1.getStyle() & SWT.CHECK) != 0);
    fullSelectionButton.setSelection((table1.getStyle() & SWT.FULL_SELECTION) != 0);
    hideSelectionButton.setSelection((table1.getStyle() & SWT.HIDE_SELECTION) != 0);
  }
}
