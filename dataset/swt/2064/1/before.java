class PlaceHold {
  public static Program[] getPrograms() {
    boolean disposeDisplay = false;
    Display display = Display.getCurrent();
    if (display == null) {
      display = new Display();
      disposeDisplay = true;
    }
    Program[] result = getPrograms(display);
    if (disposeDisplay) {
      display.dispose();
    }
    return result;
  }
}
