class PlaceHold {
  void notifyListeners(Configuration config, T val) {
    Vector<OptionListener<T>> v = listeners.get(config);
    if (v == null) {
      return;
    }
    OptionEvent<T> e = new OptionEvent<T>(this, val);
    int size = v.size();
    for (int i = 0; i < size; i++) {
      v.elementAt(i).optionChanged(e);
    }
  }
}
