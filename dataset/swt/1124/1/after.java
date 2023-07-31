class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_KEYDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (((int) (wParam))) {
      case OS.VK_SPACE:
        return LRESULT.ZERO;
      case OS.VK_ADD:
        if (OS.GetKeyState(VK_CONTROL) < 0) {
          int index = 0;
          while (index < columnCount) {
            if (!columns[index].getResizable()) {
              break;
            }
            index++;
          }
          if ((index != columnCount) || hooks(MeasureItem)) {
            TableColumn[] newColumns = new TableColumn[columnCount];
            System.arraycopy(columns, 0, newColumns, 0, columnCount);
            for (int i = 0; i < newColumns.length; i++) {
              TableColumn column = newColumns[i];
              if ((!column.isDisposed()) && column.getResizable()) {
                column.pack();
              }
            }
            return LRESULT.ZERO;
          }
        }
        break;
      case OS.VK_PRIOR:
      case OS.VK_NEXT:
      case OS.VK_HOME:
      case OS.VK_END:
        int oldHeaderProc = 0;
        int oldTableProc = 0;
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        boolean fixSubclass = isOptimizedRedraw();
        if (fixSubclass) {
          oldTableProc = OS.SetWindowLongPtr(handle, GWLP_WNDPROC, TableProc);
          oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, HeaderProc);
        }
        int code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
        result = (code == 0) ? LRESULT.ZERO : new LRESULT(code);
        if (fixSubclass) {
          OS.SetWindowLongPtr(handle, GWLP_WNDPROC, oldTableProc);
          OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, oldHeaderProc);
        }
      case OS.VK_UP:
      case OS.VK_DOWN:
        OS.SendMessage(handle, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
        break;
    }
    return result;
  }
}
