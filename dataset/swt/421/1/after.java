class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    int index;
    if (USE_PROPERTY) {
      index = OS.GetProp(hwnd, SWT_OBJECT_INDEX) - 1;
    } else {
      index = OS.GetWindowLong(hwnd, GWL_USERDATA) - 1;
    }
    if ((0 <= index) && (index < controlTable.length)) {
      Control control = controlTable[index];
      if (control != null) {
        return control.windowProc(hwnd, msg, wParam, lParam);
      }
    }
    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
  }
}
