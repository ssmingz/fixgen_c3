class PlaceHold {
  public void setSize(int width, int height) {
    Display display = getDisplay();
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    super.setSize(width, height);
    display.setWarnings(warnings);
  }
}
