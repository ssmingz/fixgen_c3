class PlaceHold {
  void setExampleWidgetVisibility() {
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setVisible(visibleButton.getSelection());
    }
  }
}
