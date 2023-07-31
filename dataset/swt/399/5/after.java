class PlaceHold {
  void setExampleWidgetVisibility() {
    Control[] controls = getExampleControls();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setVisible(visibleButton.getSelection());
    }
  }
}
