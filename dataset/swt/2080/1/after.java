class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    super.setText(string);
    char[] buffer = new char[text.length()];
    text.getChars(0, buffer.length, buffer, 0);
    int length = fixMnemonic(buffer);
    displayText = new String(buffer, 0, length);
    NSTableHeaderView headerView = ((NSOutlineView) (parent.view)).headerView();
    if (headerView == null) {
      return;
    }
    int index = ((NSOutlineView) (parent.view)).columnWithIdentifier(nsColumn);
    NSRect rect = headerView.headerRectOfColumn(index);
    headerView.setNeedsDisplayInRect(rect);
  }
}
