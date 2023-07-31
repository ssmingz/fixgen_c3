class PlaceHold {
  public void setBounds(int x, int y, int width, int height) {
    Display display = getDisplay();
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    super.setBounds(x, y, width, height);
    display.setWarnings(warnings);
  }
}
