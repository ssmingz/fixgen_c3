class PlaceHold {
  void add(int[] pointArray, int count) {
    if (count <= 2) {
      return;
    }
    int polyRgn = polyRgn(pointArray, count);
    OS.UnionRgn(handle, polyRgn, handle);
    OS.DisposeRgn(polyRgn);
  }
}
