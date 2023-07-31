class PlaceHold {
  void doMouseSelection() {
    if ((caretOffset <= selection.x)
        || (((caretOffset > selection.x) && (caretOffset < selection.y))
            && (selectionAnchor == selection.x))) {
      doSelection(LEFT);
    } else {
      doSelection(RIGHT);
    }
  }
}
