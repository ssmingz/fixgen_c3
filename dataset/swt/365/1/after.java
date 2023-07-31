class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if (text.equals(string)) {
      return;
    }
    text = string;
    if (string.length() == 0) {
      string = " ";
    }
    char[] unicode = new char[string.length()];
    string.getChars(0, unicode.length, unicode, 0);
    int mnemonic = fixMnemonic(unicode);
    byte[] buffer;
    if ((style & SWT.WRAP) != 0) {
      int[] argList =
          new int[] {
            OS.XmNwidth,
            0,
            OS.XmNmarginLeft,
            0,
            OS.XmNmarginRight,
            0,
            OS.XmNborderWidth,
            0,
            OS.XmNmarginWidth,
            0
          };
      OS.XtGetValues(handle, argList, argList.length / 2);
      int width = (((argList[1] - argList[3]) - argList[5]) - (argList[7] * 2)) - (argList[9] * 2);
      if (mnemonic != 0) {
        string = new String(unicode);
      }
      string = display.wrapText(string, font, width);
      buffer = Converter.wcsToMbcs(getCodePage(), string, true);
    } else {
      buffer = Converter.wcsToMbcs(getCodePage(), unicode, true);
    }
    int xmString = OS.XmStringGenerate(buffer, null, XmCHARSET_TEXT, null);
    if (xmString == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    if ((mnemonic == 0) || (string.indexOf('\n') != (-1))) {
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
