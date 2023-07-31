class PlaceHold {
  public void hourglassOff() {
    hourglassNestLevel--;
    if (hourglassNestLevel == 0) {
      getGlassPane().setVisible(false);
      _currentDefPane.setEditable(true);
      setAllowKeyEvents(true);
    }
  }
}
