class PlaceHold {
  static boolean gnome_init() {
    try {
      Library.loadLibrary("swt-gnome");
    } catch (Throwable e) {
      return false;
    }
    return true;
  }
}
