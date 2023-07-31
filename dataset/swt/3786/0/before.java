class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.equals(this.font)) {
      return;
    }
    this.font = font;
    if (!parent.updateTabHeight(parent.tabHeight, false)) {
      parent.updateItems();
      parent.redraw();
    }
  }
}
