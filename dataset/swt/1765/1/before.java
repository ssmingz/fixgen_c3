class PlaceHold {
  void setExampleWidgetBackground() {
    if (backgroundButton == null) {
      return;
    }
    Control[] controls = getExampleWidgets();
    Color color = backgroundColor;
    if (color == null) {
      color = controls[0].getBackground();
    }
    Image image = backgroundButton.getImage();
    drawImage(image, color);
    backgroundButton.setImage(image);
    if (backgroundColor == null) {
      return;
    }
    for (int i = 0; i < controls.length; i++) {
      Control control = controls[i];
      control.setBackground(backgroundColor);
    }
  }
}
