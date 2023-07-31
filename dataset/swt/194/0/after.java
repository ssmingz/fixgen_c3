class PlaceHold {
  LRESULT WM_KILLFOCUS(int wParam, int lParam) {
    int index = ((int) (OS.SendMessage(handle, TB_GETHOTITEM, 0, 0)));
    TBBUTTON lpButton = new TBBUTTON();
    int code = OS.SendMessage(handle, TB_GETBUTTON, index, lpButton);
    if (code != 0) {
      lastFocusId = lpButton.idCommand;
    }
    return super.WM_KILLFOCUS(wParam, lParam);
  }
}
