class PlaceHold {
  void setExampleWidgetEnabled() {
    Control[] controls = getExampleControls();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setEnabled(enabledButton.getSelection());
    }
  }
}
