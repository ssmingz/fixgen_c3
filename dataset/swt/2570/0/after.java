class PlaceHold {
  public Rectangle getBounds(int index) {
    checkWidget();
    if (!parent.checkData(this)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index < parent.columnCount))) {
      return new Rectangle(0, 0, 0, 0);
    }
    if (!OS.UIElement_IsVisible(handle)) {
      return new Rectangle(0, 0, 0, 0);
    }
    int point = OS.gcnew_Point(0, 0);
    updateLayout(handle);
    int contentPresenter = findContentPresenter(handle, index);
    int location = OS.UIElement_TranslatePoint(contentPresenter, point, parent.handle);
    int x = ((int) (OS.Point_X(location)));
    int y = ((int) (OS.Point_Y(location)));
    OS.GCHandle_Free(point);
    OS.GCHandle_Free(location);
    int width = ((int) (OS.FrameworkElement_ActualWidth(contentPresenter)));
    int height = ((int) (OS.FrameworkElement_ActualHeight(contentPresenter)));
    return new Rectangle(x, y, width, height);
  }
}
