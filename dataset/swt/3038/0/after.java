class PlaceHold {
  public void copy() {
    checkWidget();
    Point selection = getSelection();
    if (selection.x == selection.y) {
      return;
    }
    copyToClipboard(getText(selection.x, selection.y));
  }
}
