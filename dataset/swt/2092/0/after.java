class PlaceHold {
  void setSelectionHighlightGradientColor(Color start) {
    if (inDispose) {
      return;
    }
    renderer.setSelectionHighlightGradientColor(start);
  }
}
