class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (text.equals(string)) {
      return;
    }
    super.setText(string);
    if ((style & (SWT.ARROW | SWT.SEPARATOR)) != 0) {
      return;
    }
    if (OS.IsLinux && ((style & (SWT.CHECK | SWT.RADIO)) != 0)) {
      if (string.length() == 0) {
        string = " ";
      }
    }
    char[] text = new char[string.length()];
    string.getChars(0, text.length, text, 0);
    boolean accel = false;
    int i = 0;
    int j = 0;
    int mnemonic = 0;
    while (i < text.length) {
      if (text[i] == '\t') {
        accel = true;
        break;
      }
      if ((text[j++] = text[i++]) == Mnemonic) {
        if (i == text.length) {
          continue;
        }
        if (text[i] == Mnemonic) {
          i++;
          continue;
        }
        if (mnemonic == 0) {
          mnemonic = text[i];
        }
        j--;
      }
    }
    byte[] buffer2;
    if (accel && ((++i) < text.length)) {
      char[] accelText = new char[text.length - i];
      System.arraycopy(text, i, accelText, 0, accelText.length);
      buffer2 = Converter.wcsToMbcs(null, accelText, true);
    } else {
      buffer2 = new byte[1];
    }
    int xmString2 =
        OS.XmStringParseText(buffer2, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, null, 0, 0);
    if (xmString2 == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    while (j < text.length) {
      text[j++] = 0;
    }
    byte[] buffer1 = Converter.wcsToMbcs(null, text, true);
    int xmString1 =
        OS.XmStringParseText(buffer1, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, null, 0, 0);
    if (xmString1 == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    if (mnemonic == 0) {
      mnemonic = OS.XK_VoidSymbol;
    }
    int[] argList =
        new int[] {
          OS.XmNlabelType,
          OS.XmSTRING,
          OS.XmNlabelString,
          xmString1,
          OS.XmNmnemonic,
          mnemonic,
          OS.XmNacceleratorText,
          xmString2
        };
    if (OS.IsSunOS) {
      argList = new int[] {OS.XmNlabelType, OS.XmSTRING, OS.XmNlabelString, xmString1};
    }
    OS.XtSetValues(handle, argList, argList.length / 2);
    if (xmString1 != 0) {
      OS.XmStringFree(xmString1);
    }
    if (xmString2 != 0) {
      OS.XmStringFree(xmString2);
    }
  }
}
