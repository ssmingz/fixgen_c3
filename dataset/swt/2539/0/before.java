class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int strPtr = createDotNetString(string, false);
    int shellHandle = topHandle();
    OS.Window_Title(shellHandle, strPtr);
    OS.GCHandle_Free(strPtr);
  }
}
