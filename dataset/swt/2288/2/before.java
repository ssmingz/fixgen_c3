class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    ignoreSelection = true;
    int itemCount = getItemCount();
    int text = 0;
    if (textHandle != 0) {
      text = OS.TextBox_Text(textHandle);
    }
    int selectedIndex = OS.Selector_SelectedIndex(handle);
    int width = wHint;
    int height = hHint;
    if (wHint == SWT.DEFAULT) {
      width = 0x7fffffff;
    }
    if (hHint == SWT.DEFAULT) {
      height = 0x7fffffff;
    }
    width = Math.max(0, width);
    height = Math.max(0, height);
    int availSize = OS.gcnew_Size(((double) (width)), ((double) (height)));
    if (availSize == 0) {
      error(ERROR_NO_HANDLES);
    }
    double requestWidth = OS.FrameworkElement_Width(handle);
    double requestHeight = OS.FrameworkElement_Height(handle);
    int widthDP = OS.FrameworkElement_WidthProperty();
    int heightDP = OS.FrameworkElement_HeightProperty();
    OS.DependencyObject_ClearValue(handle, widthDP);
    OS.DependencyObject_ClearValue(handle, heightDP);
    OS.UIElement_Measure(handle, availSize);
    int size = OS.UIElement_DesiredSize(handle);
    width = ((int) (OS.Size_Width(size)));
    height = ((int) (OS.Size_Height(size)));
    OS.GCHandle_Free(size);
    for (int i = 0; i < itemCount; i++) {
      OS.Selector_SelectedIndex(handle, i);
      OS.UIElement_UpdateLayout(handle);
      OS.UIElement_Measure(handle, availSize);
      size = OS.UIElement_DesiredSize(handle);
      width = Math.max(width, ((int) (OS.Size_Width(size))));
      height = Math.max(height, ((int) (OS.Size_Height(size))));
      OS.GCHandle_Free(size);
    }
    OS.GCHandle_Free(availSize);
    OS.FrameworkElement_Width(handle, requestWidth);
    OS.FrameworkElement_Height(handle, requestHeight);
    OS.Selector_SelectedIndex(handle, selectedIndex);
    if (textHandle != 0) {
      OS.TextBox_Text(textHandle, text);
      OS.GCHandle_Free(text);
    }
    OS.GCHandle_Free(widthDP);
    OS.GCHandle_Free(heightDP);
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    ignoreSelection = false;
    return new Point(width, height);
  }
}
