class PlaceHold {
  public void setText(String text) {
    checkLayout();
    if (text == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (text.equals(this.text)) {
      return;
    }
    freeRuns();
    this.text = text;
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
    styles[styles.length - 1].start = text.length();
  }
}
