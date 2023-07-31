class PlaceHold {
  public boolean update() {
    for (int i = 0; i < _components.size(); i++) {
      boolean isValidUpdate = _components.elementAt(i).updateConfig();
      if (!isValidUpdate) {
        return false;
      }
    }
    return true;
  }
}
