class PlaceHold {
  void setExampleWidgetBackgroundImage() {
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setBackgroundImage(
          backgroundImageButton.getSelection()
              ? instance.images[ControlExample.ciBackground]
              : null);
    }
  }
}
