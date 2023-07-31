class PlaceHold {
  public static String[] getExtensions() {
    boolean disposeDisplay = false;
    Display display = Display.getCurrent();
    if (display == null) {
      display = new Display();
      disposeDisplay = true;
    }
    String[] result = getExtensions(display);
    if (disposeDisplay) {
      display.dispose();
    }
    return result;
  }
}
