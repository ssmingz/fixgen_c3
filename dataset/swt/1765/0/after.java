class PlaceHold {
  void setExampleWidgetForeground() {
    if (foregroundButton == null) {
      return;
    }
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      Control control = controls[i];
      control.setForeground(foregroundColor);
    }
    Color color = foregroundColor;
    if (controls.length == 0) {
      return;
    }
    if (color == null) {
      color = controls[0].getForeground();
    }
    drawImage(foregroundImage, color);
    foregroundButton.setImage(foregroundImage);
  }
}
