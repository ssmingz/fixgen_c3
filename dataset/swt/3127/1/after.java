class PlaceHold {
  void handleCompositionHittest(Event event) {
    int[] trailing = new int[1];
    int offset = getOffsetAtPoint(event.x, event.y, trailing, true);
    if (offset != (-1)) {
      if ((compositionStart <= offset) && (offset < (compositionStart + compositionLength))) {
        event.hitTest = SWT.HITTEST_INSIDE_COMPOSITION;
        event.index = offset - compositionStart;
      } else {
        event.hitTest = SWT.HITTEST_INSIDE_TEXT;
        event.index = offset;
      }
      event.trailing = trailing[0];
    } else {
      event.hitTest = SWT.HITTEST_OUTSIDE_TEXT;
    }
  }
}
