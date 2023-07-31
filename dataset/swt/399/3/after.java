class PlaceHold {
  void setExampleWidgetBackground() {
    if (colorAndFontTable == null) {
      return;
    }
    Control[] controls = getExampleControls();
    if (!instance.startup) {
      for (int i = 0; i < controls.length; i++) {
        controls[i].setBackground(backgroundColor);
      }
    }
    Color color = backgroundColor;
    if (controls.length == 0) {
      return;
    }
    if (color == null) {
      color = controls[0].getBackground();
    }
    TableItem item = colorAndFontTable.getItem(BACKGROUND_COLOR);
    Image oldImage = item.getImage();
    if (oldImage != null) {
      oldImage.dispose();
    }
    item.setImage(colorImage(color));
  }
}
