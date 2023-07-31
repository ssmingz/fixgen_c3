class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    if (echo != 0) {
      if ((echo = ((char) (wcsToMbcs(echo, getCodePage())))) == 0) {
        echo = '*';
      }
    }
    OS.SendMessage(handle, EM_SETPASSWORDCHAR, echo, 0);
    OS.InvalidateRect(handle, null, true);
  }
}
