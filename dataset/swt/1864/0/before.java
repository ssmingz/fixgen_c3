class PlaceHold {
  public void setFont(Font font) {
    if ((font != null) && font.equals(getFont())) {
      return;
    }
    super.setFont(font);
    layoutItems();
    redrawTabArea(-1);
  }
}
