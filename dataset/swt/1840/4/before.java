class PlaceHold {
  void propagateWidget(boolean enabled) {
    super.propagateWidget(enabled);
    propagateHandle(enabled, labelHandle);
  }
}
