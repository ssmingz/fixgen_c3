class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.equals(getText())) {
      return;
    }
    super.setText(string);
    shortenedText = null;
    shortenedTextWidth = 0;
    parent.updateItems();
    parent.redraw();
  }
}
