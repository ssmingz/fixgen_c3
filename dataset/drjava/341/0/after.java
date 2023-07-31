class PlaceHold {
  public void hourglassOff() {
    hourglassNestLevel--;
    if (hourglassNestLevel == 0) {
      Utilities.invokeAndWait(
          new Runnable() {
            public void run() {
              getGlassPane().setVisible(false);
              _currentDefPane.setEditable(true);
              setAllowKeyEvents(true);
            }
          });
    }
  }
}
