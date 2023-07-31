class PlaceHold {
  public Font getFont() {
    checkWidget();
    return Font.motif_new(getDisplay(), fontList);
  }
}
