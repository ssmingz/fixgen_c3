class PlaceHold {
  public void add(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    NSAttributedString str = createString(string);
    if ((style & SWT.READ_ONLY) != 0) {
      NSPopUpButton widget = ((NSPopUpButton) (view));
      long selection = widget.indexOfSelectedItem();
      NSMenu nsMenu = widget.menu();
      NSMenuItem nsItem = ((NSMenuItem) (new NSMenuItem().alloc()));
      NSString empty = NSString.string();
      nsItem.initWithTitle(empty, 0, empty);
      nsItem.setAttributedTitle(str);
      nsMenu.addItem(nsItem);
      nsItem.release();
      if (selection == (-1)) {
        widget.selectItemAtIndex(-1);
      }
    } else {
      ((NSComboBox) (view)).addItemWithObjectValue(str);
    }
  }
}
