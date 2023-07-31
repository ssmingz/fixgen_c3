class PlaceHold {
  LRESULT wmKeyDown(int hwnd, int wParam, int lParam) {
    LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    if (segments != null) {
      switch (((int) (wParam))) {
        case OS.VK_LEFT:
        case OS.VK_UP:
        case OS.VK_RIGHT:
        case OS.VK_DOWN:
          int code = 0;
          int[] start = new int[1];
          int[] end = new int[1];
          int[] newStart = new int[1];
          int[] newEnd = new int[1];
          OS.SendMessage(handle, EM_GETSEL, start, end);
          while (!isValidOffet(start[0], end[0])) {
            code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
            OS.SendMessage(handle, EM_GETSEL, newStart, newEnd);
            if ((start[0] == newStart[0]) && (end[0] == newEnd[0])) {
              break;
            }
            start[0] = newStart[0];
            end[0] = newEnd[0];
          }
          code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
          OS.SendMessage(handle, EM_GETSEL, start, end);
          while (!isValidOffet(start[0], end[0])) {
            code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
            OS.SendMessage(handle, EM_GETSEL, newStart, newEnd);
            if ((start[0] == newStart[0]) && (end[0] == newEnd[0])) {
              break;
            }
            start[0] = newStart[0];
            end[0] = newEnd[0];
          }
          result = (code == 0) ? LRESULT.ZERO : new LRESULT(code);
      }
    }
    return result;
  }
}
