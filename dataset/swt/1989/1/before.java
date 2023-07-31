class PlaceHold {
  LRESULT WM_GETDLGCODE(int wParam, int lParam) {
    LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.IsPPC) {
      if ((((style & SWT.MULTI) != 0) && ((style & SWT.READ_ONLY) == 0)) && (lParam == 0)) {
        return new LRESULT((OS.DLGC_HASSETSEL | OS.DLGC_WANTALLKEYS) | OS.DLGC_WANTCHARS);
      }
    }
    if ((style & SWT.READ_ONLY) != 0) {
      int code = callWindowProc(WM_GETDLGCODE, wParam, lParam);
      code &= ~(OS.DLGC_WANTALLKEYS | OS.DLGC_WANTTAB);
      return new LRESULT(code);
    }
    return null;
  }
}
