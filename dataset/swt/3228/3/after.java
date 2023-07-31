class PlaceHold {
  public String getText(int index) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (index == 0) {
      return getText();
    }
    if (strings != null) {
      if ((0 <= index) && (index < strings.length)) {
        String string = strings[index];
        return string != null ? string : "";
      }
    }
    return "";
  }
}
