class PlaceHold {
  private static int find(int cHandle, Rectangle parentBounds, MacRect tmp, Point where) {
    if (!OS.IsControlVisible(cHandle)) {
      return 0;
    }
    if (!OS.IsControlActive(cHandle)) {
      return 0;
    }
    OS.GetControlBounds(cHandle, tmp.getData());
    Rectangle rr = tmp.toRectangle();
    if (parentBounds != null) {
      rr = parentBounds.intersection(rr);
    }
    int n = countSubControls(cHandle);
    if (n > 0) {
      int[] outHandle = new int[1];
      for (int i = n; i > 0; i--) {
        if (OS.GetIndexedSubControl(cHandle, ((short) (i)), outHandle) == 0) {
          int result = find(outHandle[0], rr, tmp, where);
          if (result != 0) {
            return result;
          }
        }
      }
    }
    if (rr.contains(where)) {
      return cHandle;
    }
    return 0;
  }
}
