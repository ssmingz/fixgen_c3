class PlaceHold {
  void setExampleWidgetBackground() {
    if (backgroundButton == null) {
      return;
    }
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      Control control = controls[i];
      control.setBackground(backgroundColor);
    }
    Color color = backgroundColor;
    if (controls.length == 0) {
      return;
    }
    if (color == null) {
      color = controls[0].getBackground();
    }
    drawImage(backgroundImage, color);
    backgroundButton.setImage(backgroundImage);
  }
}
