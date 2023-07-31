class PlaceHold {
  public String getText() {
    checkWidget();
    if ((style & SWT.ARROW) != 0) {
      return "";
    }
    int[] argList = new int[] {OS.XmNlabelString, 0, OS.XmNmnemonic, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    int xmString = argList[1];
    int mnemonic = argList[3];
    if (mnemonic == OS.XK_VoidSymbol) {
      mnemonic = 0;
    }
    if (xmString == 0) {
      error(ERROR_CANNOT_GET_TEXT);
    }
    char[] result = null;
    int address =
        OS.XmStringUnparse(xmString, null, XmCHARSET_TEXT, XmCHARSET_TEXT, null, 0, XmOUTPUT_ALL);
    if (address != 0) {
      int length = OS.strlen(address);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, address, length);
      OS.XtFree(address);
      result = Converter.mbcsToWcs(null, buffer);
    }
    if (xmString != 0) {
      OS.XmStringFree(xmString);
    }
    int count = 0;
    if (mnemonic != 0) {
      count++;
    }
    for (int i = 0; i < (result.length - 1); i++) {
      if (result[i] == Mnemonic) {
        count++;
      }
    }
    char[] newResult = result;
    if ((count != 0) || (mnemonic != 0)) {
      newResult = new char[result.length + count];
      int i = 0;
      int j = 0;
      while (i < result.length) {
        if ((mnemonic != 0) && (result[i] == mnemonic)) {
          if (j < newResult.length) {
            newResult[j++] = Mnemonic;
          }
          mnemonic = 0;
        }
        if ((newResult[j++] = result[i++]) == Mnemonic) {
          if (j < newResult.length) {
            newResult[j++] = Mnemonic;
          }
        }
      }
    }
    return new String(newResult);
  }
}
