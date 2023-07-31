class PlaceHold {
  LRESULT WM_CUT(int wParam, int lParam) {
    LRESULT result = super.WM_CUT(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return result;
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.ES_READONLY) != 0) {
      return result;
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    if (start[0] == end[0]) {
      return result;
    }
    String newText = verifyText("", start[0], end[0], null);
    if (newText == null) {
      return LRESULT.ZERO;
    }
    if (newText.length() != 0) {
      result = new LRESULT(callWindowProc(WM_CUT, 0, 0));
      newText = Display.withCrLf(newText);
      TCHAR buffer = new TCHAR(getCodePage(), newText, true);
      ignoreCharacter = true;
      OS.SendMessage(handle, EM_REPLACESEL, 0, buffer);
      ignoreCharacter = false;
    }
    return result;
  }
}
