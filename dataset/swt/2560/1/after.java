class PlaceHold {
  void setExampleWidgetBackgroundImage() {
    if (backgroundImageButton.isDisposed()) {
      return;
    }
    Control[] controls = getExampleControls();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setBackgroundImage(
          backgroundImageButton.getSelection()
              ? instance.images[ControlExample.ciBackground]
              : null);
    }
  }
}
