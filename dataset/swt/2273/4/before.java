class PlaceHold {
  public void setText(String string) {
    checkWidget();
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
