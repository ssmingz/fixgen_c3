class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    if (!instance.startup) {
      setWidgetIncrement();
      setWidgetPageIncrement();
      setWidgetThumb();
    }
  }
}
