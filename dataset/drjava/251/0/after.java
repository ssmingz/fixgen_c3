class PlaceHold {
  public void resetToCurrent() {
    for (int i = 0; i < _components.size(); i++) {
      _components.get(i).resetToCurrent();
    }
  }
}
