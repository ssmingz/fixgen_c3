class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.equals(getFont())) {
      return;
    }
    super.setFont(font);
    oldFont = getFont();
    if (!updateTabHeight(tabHeight, false)) {
      updateItems();
      redraw();
    }
  }
}
