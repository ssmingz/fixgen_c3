class PlaceHold {
  LRESULT wmPrint(int hwnd, int wParam, int lParam) {
    if ((lParam & OS.PRF_NONCLIENT) != 0) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        int bits = OS.GetWindowLong(hwnd, GWL_EXSTYLE);
        if ((bits & OS.WS_EX_CLIENTEDGE) != 0) {
          int code = callWindowProc(hwnd, WM_PRINT, wParam, lParam);
          RECT rect = new RECT();
          OS.GetWindowRect(hwnd, rect);
          rect.right -= rect.left;
          rect.bottom -= rect.top;
          rect.left = rect.top = 0;
          int border = OS.GetSystemMetrics(SM_CXEDGE);
          OS.ExcludeClipRect(wParam, border, border, rect.right - border, rect.bottom - border);
          OS.DrawThemeBackground(display.hEditTheme(), wParam, EP_EDITTEXT, ETS_NORMAL, rect, null);
          return new LRESULT(code);
        }
      }
    }
    return null;
  }
}
