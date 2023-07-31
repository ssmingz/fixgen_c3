class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    int hItem = 0;
    if (redraw) {
      if (drawCount == 1) {
        int count = ((int) (OS.SendMessage(handle, TVM_GETCOUNT, 0, 0)));
        if (count == 0) {
          TVINSERTSTRUCT tvInsert = new TVINSERTSTRUCT();
          tvInsert.hInsertAfter = OS.TVI_FIRST;
          hItem = OS.SendMessage(handle, TVM_INSERTITEM, 0, tvInsert);
        }
        OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
        updateScrollBar();
      }
    }
    super.setRedraw(redraw);
    if (!redraw) {
      if (drawCount == 1) {
        OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
      }
    }
    if (hItem != 0) {
      ignoreShrink = true;
      OS.SendMessage(handle, TVM_DELETEITEM, 0, hItem);
      ignoreShrink = false;
    }
  }
}
