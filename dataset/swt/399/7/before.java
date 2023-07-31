class PlaceHold {
  void setExampleWidgetEnabled() {
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      controls[i].setEnabled(enabledButton.getSelection());
    }
  }
}
