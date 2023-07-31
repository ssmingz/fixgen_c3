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
    checkButton.setSelection((tree1.getStyle() & SWT.CHECK) != 0);
    checkButton.setSelection((tree2.getStyle() & SWT.CHECK) != 0);
    fullSelectionButton.setSelection((tree1.getStyle() & SWT.FULL_SELECTION) != 0);
    fullSelectionButton.setSelection((tree2.getStyle() & SWT.FULL_SELECTION) != 0);
  }
}
