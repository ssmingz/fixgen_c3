class PlaceHold {
  static boolean gnome_init() {
    try {
      Callback.loadLibrary("swt-gnome");
    } catch (SWTError e) {
      return false;
    }
    return true;
  }
}
