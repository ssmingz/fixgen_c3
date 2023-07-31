class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    text = string;
    char[] text = new char[string.length()];
    string.getChars(0, text.length, text, 0);
    int mnemonic = fixMnemonic(text);
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), text, true);
    int xmString =
        OS.XmStringParseText(buffer, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, null, 0, 0);
    if (xmString == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    if (mnemonic == 0) {
      mnemonic = OS.XK_VoidSymbol;
    }
    int[] argList =
        new int[] {
          OS.XmNlabelType, OS.XmSTRING, OS.XmNlabelString, xmString, OS.XmNmnemonic, mnemonic
        };
    OS.XtSetValues(handle, argList, argList.length / 2);
    if (xmString != 0) {
      OS.XmStringFree(xmString);
    }
  }
}
