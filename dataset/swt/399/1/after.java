class PlaceHold {
  void setExampleWidgetForeground() {
    if (colorAndFontTable == null) {
      return;
    }
    Control[] controls = getExampleControls();
    if (!instance.startup) {
      for (int i = 0; i < controls.length; i++) {
        controls[i].setForeground(foregroundColor);
      }
    }
    Color color = foregroundColor;
    if (controls.length == 0) {
      return;
    }
    if (color == null) {
      color = controls[0].getForeground();
    }
    TableItem item = colorAndFontTable.getItem(FOREGROUND_COLOR);
    Image oldImage = item.getImage();
    if (oldImage != null) {
      oldImage.dispose();
    }
    item.setImage(colorImage(color));
  }
}
