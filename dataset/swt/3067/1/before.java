class PlaceHold {
  static void unregisterAccessible(Accessible accessible) {
    int widget = accessible.getControlHandle();
    Accessibles.remove(new LONG(widget));
  }
}
