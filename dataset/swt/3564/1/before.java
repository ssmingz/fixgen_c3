class PlaceHold {
  public static int getVisibleRegion(int cHandle, int result, boolean includingTop) {
    int tmpRgn = OS.NewRgn();
    getControlRegion(cHandle, kControlEntireControl, result);
    int parent = cHandle;
    while ((parent = MacUtil.getSuperControl(parent)) != 0) {
      getControlRegion(parent, kControlContentMetaPart, tmpRgn);
      OS.SectRgn(result, tmpRgn, result);
    }
    if (includingTop) {
      int n = countSubControls(cHandle);
      if (n > 0) {
        int[] outHandle = new int[1];
        for (int i = 0; i < n; i++) {
          int index = (REVERSE) ? n - i : i + 1;
          if (OS.GetIndexedSubControl(cHandle, ((short) (index)), outHandle) == 0) {
            if (OS.IsControlVisible(outHandle[0])) {
              getControlRegion(outHandle[0], kControlStructureMetaPart, tmpRgn);
              OS.DiffRgn(result, tmpRgn, result);
            }
          } else {
            throw new SWTError();
          }
        }
      }
    }
    OS.DisposeRgn(tmpRgn);
    return OS.kNoErr;
  }
}
