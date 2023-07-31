class PlaceHold {
  LRESULT WM_PASTE(int wParam, int lParam) {
    LRESULT result = super.WM_PASTE(wParam, lParam);
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
    String oldText = getClipboardText();
    if (oldText == null) {
      return result;
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    String newText = verifyText(oldText, start[0], end[0], null);
    if (newText == null) {
      return LRESULT.ZERO;
    }
    if (newText != oldText) {
      newText = Display.withCrLf(newText);
      TCHAR buffer = new TCHAR(getCodePage(), newText, true);
      ignoreCharacter = true;
      OS.SendMessage(handle, EM_REPLACESEL, 0, buffer);
      ignoreCharacter = false;
      return LRESULT.ZERO;
    }
    return result;
  }
}
