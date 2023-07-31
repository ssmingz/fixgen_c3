class PlaceHold {
  public void setSelectionBackground(Image image) {
    checkWidget();
    if (image == selectionBgImage) {
      return;
    }
    if (image != null) {
      selectionGradientColors = null;
      selectionGradientPercents = null;
    }
    selectionBgImage = image;
    if (selectedIndex > (-1)) {
      redraw();
    }
  }
}
