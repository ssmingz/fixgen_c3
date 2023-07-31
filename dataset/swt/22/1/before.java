class PlaceHold {
  LRESULT wmNCPaint(int hwnd, int wParam, int lParam) {
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      int bits1 = OS.GetWindowLong(hwnd, GWL_EXSTYLE);
      if ((bits1 & OS.WS_EX_CLIENTEDGE) != 0) {
        int code = 0;
        int bits2 = OS.GetWindowLong(hwnd, GWL_STYLE);
        if ((bits2 & (OS.WS_HSCROLL | OS.WS_VSCROLL)) != 0) {
          code = callWindowProc(hwnd, WM_NCPAINT, wParam, lParam);
        }
        int hDC = OS.GetWindowDC(hwnd);
        RECT rect = new RECT();
        OS.GetWindowRect(hwnd, rect);
        rect.right -= rect.left;
        rect.bottom -= rect.top;
        rect.left = rect.top = 0;
        int border = OS.GetSystemMetrics(SM_CXEDGE);
        OS.ExcludeClipRect(hDC, border, border, rect.right - border, rect.bottom - border);
        int hTheme = OS.OpenThemeData(hwnd, EDIT);
        OS.DrawThemeBackground(hTheme, hDC, EP_EDITTEXT, ETS_NORMAL, rect, null);
        OS.CloseThemeData(hwnd);
        OS.ReleaseDC(hwnd, hDC);
        return new LRESULT(code);
      }
    }
    return null;
  }
}
