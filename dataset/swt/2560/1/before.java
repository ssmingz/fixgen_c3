class PlaceHold {
  void setExampleWidgetBackgroundImage() {
    Control[] controls = getExampleControls();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setBackgroundImage(
          backgroundImageButton.getSelection()
              ? instance.images[ControlExample.ciBackground]
              : null);
    }
  }
}
