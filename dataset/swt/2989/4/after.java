class PlaceHold {
  void setFont(NSFont font) {
    super.setFont(font);
    float ascent = font.ascender();
    float descent = (-font.descender()) + font.leading();
    ((NSTableView) (view)).setRowHeight(((int) (Math.ceil(ascent + descent))) + 1);
    setScrollWidth();
  }
}
