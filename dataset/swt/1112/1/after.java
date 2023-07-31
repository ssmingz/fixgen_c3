class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (window == null) {
      return;
    }
    super.setText(string);
    NSString str = NSString.stringWith(string);
    window.setTitle(str);
  }
}
