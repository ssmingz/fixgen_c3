class PlaceHold {
  public void deselectAll() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      ((NSPopUpButton) (view)).selectItem(null);
      sendEvent(Modify);
    } else {
      NSComboBox widget = ((NSComboBox) (view));
      int index = widget.indexOfSelectedItem();
      if (index != (-1)) {
        widget.deselectItemAtIndex(index);
      }
    }
  }
}
