class PlaceHold {
  public Vector<BuildListener> getBuildListeners() {
    synchronized (listenersLock) {
      final Vector<BuildListener> r = new Vector<BuildListener>(listeners.length);
      for (int i = 0; i < listeners.length; i++) {
        r.add(listeners[i]);
      }
      return r;
    }
  }
}
