class PlaceHold {
  public void hourglassOn() {
    hourglassNestLevel++;
    if (hourglassNestLevel == 1) {
      Utilities.invokeAndWait(
          new Runnable() {
            public void run() {
              getGlassPane().setVisible(true);
              _currentDefPane.setEditable(false);
              setAllowKeyEvents(false);
            }
          });
    }
  }
}
