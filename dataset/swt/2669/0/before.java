class PlaceHold {
  public Control getCursorControl() {
    checkDevice();
    int inputElement = 0;
    int captured = OS.Mouse_Captured();
    if (captured != 0) {
      int sources = OS.PresentationSource_CurrentSources();
      int enumerator = OS.IEnumerable_GetEnumerator(sources);
      while (OS.IEnumerator_MoveNext(enumerator) && (inputElement == 0)) {
        int current = OS.IEnumerator_Current(enumerator);
        int root = OS.PresentationSource_RootVisual(current);
        if (root != 0) {
          int pt = OS.Mouse_GetPosition(root);
          inputElement = OS.UIElement_InputHitTest(root, pt);
          OS.GCHandle_Free(pt);
          OS.GCHandle_Free(root);
        }
        OS.GCHandle_Free(current);
      }
      OS.GCHandle_Free(enumerator);
      OS.GCHandle_Free(sources);
    } else {
      inputElement = OS.Mouse_DirectlyOver();
    }
    if (inputElement != 0) {
      Widget widget = getWidget(inputElement);
      OS.GCHandle_Free(inputElement);
      if (widget != null) {
        return widget.getWidgetControl();
      }
    }
    return null;
  }
}
