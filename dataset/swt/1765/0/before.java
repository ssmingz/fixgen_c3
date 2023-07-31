class PlaceHold {
  void setExampleWidgetForeground() {
    if (foregroundButton == null) {
      return;
    }
    Control[] controls = getExampleWidgets();
    Color color = foregroundColor;
    if (color == null) {
      color = controls[0].getForeground();
    }
    Image image = foregroundButton.getImage();
    drawImage(image, color);
    foregroundButton.setImage(image);
    if (foregroundColor == null) {
      return;
    }
    for (int i = 0; i < controls.length; i++) {
      Control control = controls[i];
      control.setForeground(foregroundColor);
    }
  }
}
