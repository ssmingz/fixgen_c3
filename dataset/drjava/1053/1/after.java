class PlaceHold {
  public void resetToDefault() {
    for (int i = 0; i < _components.size(); i++) {
      _components.get(i).resetToDefault();
    }
  }
}
