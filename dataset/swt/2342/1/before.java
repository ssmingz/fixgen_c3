class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    if (tip != null) {
      return tip.getVisible();
    }
    if (display.helpControl == this) {
      int window = OS.FrontWindow();
      int[] windowClass = new int[1];
      OS.GetWindowClass(window, windowClass);
      return windowClass[0] == OS.kHelpWindowClass;
    }
    return false;
  }
}
