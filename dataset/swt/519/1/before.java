class PlaceHold {
  void setTXNText(int iStartOffset, int iEndOffset, String string) {
    char[] buffer = new char[string.length()];
    string.getChars(0, buffer.length, buffer, 0);
    boolean readOnly = (style & SWT.READ_ONLY) != 0;
    int[] tag = new int[] {OS.kTXNIOPrivilegesTag};
    if (readOnly) {
      OS.TXNSetTXNObjectControls(txnObject, false, 1, tag, new int[] {0});
    }
    OS.TXNSetData(
        txnObject, kTXNUnicodeTextData, buffer, buffer.length * 2, iStartOffset, iEndOffset);
    if (readOnly) {
      OS.TXNSetTXNObjectControls(txnObject, false, 1, tag, new int[] {1});
    }
  }
}
