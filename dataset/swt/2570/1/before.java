class PlaceHold {
  int getScrollBarHandle(int style) {
    int scrollbar = 0;
    if (scrolledHandle != 0) {
      int children = OS.Panel_Children(scrolledHandle);
      int enumerator = OS.UIElementCollection_GetEnumerator(children);
      int scrollType = OS.ScrollBar_typeid();
      while (OS.IEnumerator_MoveNext(enumerator)) {
        int current = OS.IEnumerator_Current(enumerator);
        if (OS.Type_IsInstanceOfType(scrollType, current)) {
          int orientation = OS.ScrollBar_Orientation(current);
          if (((style & SWT.H_SCROLL) != 0) && (orientation == OS.Orientation_Horizontal)) {
            scrollbar = current;
            break;
          }
          if (((style & SWT.V_SCROLL) != 0) && (orientation == OS.Orientation_Vertical)) {
            scrollbar = current;
            break;
          }
        }
        OS.GCHandle_Free(current);
      }
      OS.GCHandle_Free(scrollType);
      OS.GCHandle_Free(enumerator);
      OS.GCHandle_Free(children);
    } else {
      if (!OS.FrameworkElement_IsLoaded(handle)) {
        OS.UIElement_UpdateLayout(handle);
      }
      int scrollViewerType = OS.ScrollViewer_typeid();
      int scrollViewer = findScrollViewer(handle, scrollViewerType);
      int template = OS.Control_Template(scrollViewer);
      int part;
      if ((style & SWT.H_SCROLL) != 0) {
        part = createDotNetString("PART_HorizontalScrollBar", false);
      } else {
        part = createDotNetString("PART_VerticalScrollBar", false);
      }
      scrollbar = OS.FrameworkTemplate_FindName(template, part, scrollViewer);
      OS.GCHandle_Free(part);
      OS.GCHandle_Free(template);
      OS.GCHandle_Free(scrollViewer);
      OS.GCHandle_Free(scrollViewerType);
    }
    return scrollbar;
  }
}
