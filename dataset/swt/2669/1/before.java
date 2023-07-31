class PlaceHold {
  void drawRectangles() {
    Rectangle bounds = this.bounds;
    if (bounds == null) {
      return;
    }
    int children = OS.Panel_Children(canvasHandle);
    OS.UIElementCollection_Clear(children);
    if (parent != null) {
      Rectangle rect = parent.getClientArea();
      rect.intersect(bounds);
      bounds = rect;
      Point pt = display.map(parent, null, bounds.x, bounds.y);
      OS.Popup_HorizontalOffset(handle, pt.x);
      OS.Popup_VerticalOffset(handle, pt.y);
    } else {
      OS.Popup_HorizontalOffset(handle, bounds.x);
      OS.Popup_VerticalOffset(handle, bounds.y);
    }
    OS.FrameworkElement_Width(handle, bounds.width);
    OS.FrameworkElement_Height(handle, bounds.height);
    int stroke;
    int brush;
    if (stippled) {
      stroke = 3;
      int pixelFormat = OS.PixelFormats_BlackWhite();
      byte[] buffer = new byte[] {-86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0};
      int image = OS.BitmapSource_Create(8, 8, 96, 96, pixelFormat, 0, buffer, buffer.length, 2);
      OS.GCHandle_Free(pixelFormat);
      brush = OS.gcnew_ImageBrush(image);
      OS.TileBrush_TileMode(brush, TileMode_Tile);
      OS.TileBrush_Stretch(brush, Stretch_Fill);
      OS.TileBrush_ViewportUnits(brush, BrushMappingMode_Absolute);
      int rect =
          OS.gcnew_Rect(
              0, 0, OS.BitmapSource_PixelWidth(image), OS.BitmapSource_PixelHeight(image));
      OS.TileBrush_Viewport(brush, rect);
      OS.GCHandle_Free(rect);
      OS.GCHandle_Free(image);
    } else {
      stroke = 1;
      brush = OS.Brushes_Black();
    }
    for (int i = 0; i < rectangles.length; i++) {
      int child = OS.gcnew_Rectangle();
      OS.UIElementCollection_Add(children, child);
      OS.Shape_StrokeThickness(child, stroke);
      OS.Shape_Stroke(child, brush);
      Rectangle rect = rectangles[i];
      OS.Canvas_SetLeft(child, rect.x - bounds.x);
      OS.Canvas_SetTop(child, rect.y - bounds.y);
      OS.FrameworkElement_Width(child, rect.width);
      OS.FrameworkElement_Height(child, rect.height);
      OS.GCHandle_Free(child);
    }
    OS.GCHandle_Free(brush);
  }
}
