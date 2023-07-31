class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if ((!checkText(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    if (transferData.type == COMPOUND_TEXT_ID) {
      Display display = Display.getCurrent();
      if (display == null) {
        return;
      }
      int xDisplay = display.xDisplay;
      int pBuffer = OS.XtMalloc(buffer.length);
      if (pBuffer == 0) {
        return;
      }
      try {
        OS.memmove(pBuffer, buffer, buffer.length);
        int list = OS.XtMalloc(4);
        if (list == 0) {
          return;
        }
        OS.memmove(list, new int[] {pBuffer}, 4);
        XTextProperty text_prop_return = new XTextProperty();
        int result =
            OS.XmbTextListToTextProperty(xDisplay, list, 1, XCompoundTextStyle, text_prop_return);
        OS.XtFree(list);
        if (result != 0) {
          return;
        }
        transferData.format = text_prop_return.format;
        transferData.length = text_prop_return.nitems;
        transferData.pValue = text_prop_return.value;
        transferData.type = text_prop_return.encoding;
        transferData.result = 1;
      } finally {
        OS.XtFree(pBuffer);
      }
    }
    if (transferData.type == STRING_ID) {
      int pValue = OS.XtMalloc(buffer.length);
      if (pValue == 0) {
        return;
      }
      OS.memmove(pValue, buffer, buffer.length);
      transferData.type = STRING_ID;
      transferData.format = 8;
      transferData.length = buffer.length - 1;
      transferData.pValue = pValue;
      transferData.result = 1;
    }
  }
}
