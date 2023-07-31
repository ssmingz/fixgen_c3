class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleLoaded");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.FrameworkElement_Loaded(handle, handler);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_RoutedPropertyChangedEventHandlerObject(jniRef, "HandleSelectedItemChanged");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.TreeView_SelectedItemChanged(handle, handler);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_MouseButtonEventHandler(jniRef, "HandleMouseDoubleClick");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.Control_MouseDoubleClick(handle, handler);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_MouseButtonEventHandler(jniRef, "HandlePreviewMouseDoubleClick");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.Control_PreviewMouseDoubleClick(handle, handler);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleExpanded");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    int event = OS.TreeViewItem_ExpandedEvent();
    OS.UIElement_AddHandler(handle, event, handler);
    OS.GCHandle_Free(event);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleCollapsed");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    event = OS.TreeViewItem_CollapsedEvent();
    OS.UIElement_AddHandler(handle, event, handler);
    OS.GCHandle_Free(event);
    OS.GCHandle_Free(handler);
    if ((style & SWT.CHECK) != 0) {
      handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleChecked");
      if (handler == 0) {
        error(ERROR_NO_HANDLES);
      }
      event = OS.ToggleButton_CheckedEvent();
      OS.UIElement_AddHandler(handle, event, handler);
      OS.GCHandle_Free(event);
      OS.GCHandle_Free(handler);
      handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleUnchecked");
      if (handler == 0) {
        error(ERROR_NO_HANDLES);
      }
      event = OS.ToggleButton_UncheckedEvent();
      OS.UIElement_AddHandler(handle, event, handler);
      OS.GCHandle_Free(event);
      OS.GCHandle_Free(handler);
      handler = OS.gcnew_RoutedEventHandler(jniRef, "HandleIndeterminate");
      if (handler == 0) {
        error(ERROR_NO_HANDLES);
      }
      event = OS.ToggleButton_IndeterminateEvent();
      OS.UIElement_AddHandler(handle, event, handler);
      OS.GCHandle_Free(event);
      OS.GCHandle_Free(handler);
    }
  }
}
