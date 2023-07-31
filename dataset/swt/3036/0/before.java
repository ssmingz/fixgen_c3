class PlaceHold {
  void createHandle() {
    NSScrollView scrollWidget = ((NSScrollView) (new SWTScrollView().alloc()));
    scrollWidget.init();
    scrollWidget.setHasHorizontalScroller((style & SWT.H_SCROLL) != 0);
    scrollWidget.setHasVerticalScroller((style & SWT.V_SCROLL) != 0);
    scrollWidget.setAutohidesScrollers(true);
    scrollWidget.setBorderType(hasBorder() ? OS.NSBezelBorder : OS.NSNoBorder);
    NSOutlineView widget = ((NSOutlineView) (new SWTOutlineView().alloc()));
    widget.initWithFrame(new NSRect());
    widget.setAllowsMultipleSelection((style & SWT.MULTI) != 0);
    widget.setAllowsColumnReordering(false);
    widget.setAutoresizesOutlineColumn(false);
    widget.setAutosaveExpandedItems(true);
    widget.setDataSource(widget);
    widget.setDelegate(widget);
    widget.setColumnAutoresizingStyle(NSTableViewNoColumnAutoresizing);
    NSSize spacing = new NSSize();
    spacing.width = spacing.height = CELL_GAP;
    widget.setIntercellSpacing(spacing);
    widget.setDoubleAction(sel_sendDoubleSelection);
    if (!hasBorder()) {
      widget.setFocusRingType(NSFocusRingTypeNone);
    }
    headerView = ((NSTableHeaderView) (new SWTTableHeaderView().alloc().init()));
    widget.setHeaderView(null);
    NSString str = NSString.stringWith("");
    if ((style & SWT.CHECK) != 0) {
      checkColumn = ((NSTableColumn) (new NSTableColumn().alloc()));
      checkColumn = checkColumn.initWithIdentifier(NSString.stringWith(String.valueOf(++NEXT_ID)));
      checkColumn.headerCell().setTitle(str);
      widget.addTableColumn(checkColumn);
      widget.setOutlineTableColumn(checkColumn);
      checkColumn.setResizingMask(NSTableColumnNoResizing);
      checkColumn.setEditable(false);
      int cls = NSButton.cellClass();
      buttonCell = new NSButtonCell(OS.class_createInstance(cls, 0));
      buttonCell.init();
      checkColumn.setDataCell(buttonCell);
      buttonCell.setButtonType(NSSwitchButton);
      buttonCell.setImagePosition(NSImageOnly);
      buttonCell.setAllowsMixedState(true);
      checkColumn.setWidth(getCheckColumnWidth());
    }
    firstColumn = ((NSTableColumn) (new NSTableColumn().alloc()));
    firstColumn = firstColumn.initWithIdentifier(NSString.stringWith(String.valueOf(++NEXT_ID)));
    firstColumn.setMinWidth(FIRST_COLUMN_MINIMUM_WIDTH);
    firstColumn.setWidth(0);
    firstColumn.headerCell().setTitle(str);
    widget.addTableColumn(firstColumn);
    widget.setOutlineTableColumn(firstColumn);
    dataCell = ((NSTextFieldCell) (new SWTImageTextCell().alloc().init()));
    dataCell.setLineBreakMode(NSLineBreakByClipping);
    firstColumn.setDataCell(dataCell);
    scrollView = scrollWidget;
    view = widget;
  }
}
