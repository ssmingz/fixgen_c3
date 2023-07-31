class PlaceHold {
  void drawPolyLineSegment(int[] pointArray, boolean closed, boolean stroked) {
    if (pointArray.length < 4) {
      return;
    }
    int list = OS.gcnew_PointCollection(pointArray.length / 2);
    double offset = 0;
    if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
      offset = 0.5;
    }
    for (int i = 0; i < pointArray.length; i += 2) {
      int point = OS.gcnew_Point(pointArray[i] + offset, pointArray[i + 1] + offset);
      OS.PointCollection_Add(list, point);
      OS.GCHandle_Free(point);
    }
    int poly = OS.gcnew_PolyLineSegment(list, stroked);
    OS.GCHandle_Free(list);
    int figure = OS.gcnew_PathFigure();
    int startPoint = OS.gcnew_Point(pointArray[0], pointArray[1]);
    OS.PathFigure_StartPoint(figure, startPoint);
    OS.PathFigure_IsClosed(figure, closed);
    int segments = OS.PathFigure_Segments(figure);
    OS.PathSegmentCollection_Add(segments, poly);
    int path = OS.gcnew_PathGeometry();
    if (!stroked) {
      OS.PathGeometry_FillRule(
          path, data.fillRule == SWT.FILL_EVEN_ODD ? OS.FillRule_EvenOdd : OS.FillRule_Nonzero);
    }
    int figures = OS.PathGeometry_Figures(path);
    OS.PathFigureCollection_Add(figures, figure);
    OS.DrawingContext_DrawGeometry(
        handle, stroked ? 0 : data.currentBrush, stroked ? data.pen : 0, path);
    OS.GCHandle_Free(figures);
    OS.GCHandle_Free(path);
    OS.GCHandle_Free(segments);
    OS.GCHandle_Free(figure);
    OS.GCHandle_Free(startPoint);
    OS.GCHandle_Free(poly);
  }
}
