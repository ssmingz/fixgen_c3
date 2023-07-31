class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    int index = OS.GetWindowLong(hwnd, GWL_USERDATA) - 1;
    if ((0 <= index) && (index < controlTable.length)) {
      Control control = controlTable[index];
      if (control != null) {
        return control.windowProc(msg, wParam, lParam);
      }
    }
    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
  }
}
