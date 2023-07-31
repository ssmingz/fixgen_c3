class PlaceHold {
  void updateUIState() {
    int hwndShell = getShell().handle;
    int uiState = ((int) (OS.SendMessage(hwndShell, WM_QUERYUISTATE, 0, 0)));
    if ((uiState & OS.UISF_HIDEFOCUS) != 0) {
      OS.SendMessage(hwndShell, WM_CHANGEUISTATE, OS.MAKEWPARAM(UIS_CLEAR, UISF_HIDEFOCUS), 0);
    }
  }
}
