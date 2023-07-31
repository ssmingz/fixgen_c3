class PlaceHold {
  public void setText(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (index == 0) {
      if (string.equals(text)) {
        return;
      }
      width = -1;
      super.setText(string);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 <= index) && (index < count)) {
      if (strings == null) {
        strings = new String[count];
      }
      if (string.equals(strings[index])) {
        return;
      }
      strings[index] = string;
    }
    cached = true;
    if (index == 0) {
      parent.setScrollWidth(this);
    }
    ((NSOutlineView) (parent.view)).reloadItem_(handle);
  }
}
