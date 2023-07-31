class PlaceHold {
  public void add(String string, int index) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int count = getItemCount();
    if ((0 > index) || (index > count)) {
      error(ERROR_INVALID_RANGE);
    }
    NSAttributedString str = createString(string);
    if ((style & SWT.READ_ONLY) != 0) {
      NSPopUpButton widget = ((NSPopUpButton) (view));
      int selection = widget.indexOfSelectedItem();
      NSMenu nsMenu = widget.menu();
      NSMenuItem nsItem = ((NSMenuItem) (new NSMenuItem().alloc()));
      NSString empty = NSString.string();
      nsItem.initWithTitle(empty, 0, empty);
      nsItem.setAttributedTitle(str);
      nsMenu.insertItem(nsItem, index);
      nsItem.release();
      if (selection == (-1)) {
        widget.selectItemAtIndex(-1);
      }
    } else {
      ((NSComboBox) (view)).insertItemWithObjectValue(str, index);
    }
  }
}
