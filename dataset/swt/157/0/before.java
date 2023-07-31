class PlaceHold {
  void setFont(NSFont font) {
    if (textView != null) {
      NSCell cell = new NSCell(textView.cell());
      cell.setAttributedStringValue(createString());
    }
  }
}
