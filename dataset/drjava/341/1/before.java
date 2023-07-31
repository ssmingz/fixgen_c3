class PlaceHold {
  public void hourglassOn() {
    hourglassNestLevel++;
    if (hourglassNestLevel == 1) {
      getGlassPane().setVisible(true);
      _currentDefPane.setEditable(false);
      setAllowKeyEvents(false);
    }
  }
}
