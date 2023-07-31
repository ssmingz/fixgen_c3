class PlaceHold {
  public String getText(int index) {
    checkWidget();
    if (index == 0) {
      return super.getText();
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
