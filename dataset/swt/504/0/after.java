class PlaceHold {
  void doMouseSelection() {
    if ((caretOffset <= selection.x)
        || (((caretOffset > selection.x) && (caretOffset < selection.y))
            && (selectionAnchor == selection.x))) {
      doSelection(COLUMN_PREVIOUS);
    } else {
      doSelection(COLUMN_NEXT);
    }
  }
}
