class PlaceHold {
  public int Confirm(int parent, int dialogTitle, int text, int _retval) {
    Browser browser = getBrowser(parent);
    int length = XPCOM.strlen_PRUnichar(dialogTitle);
    char[] dest = new char[length];
    XPCOM.memmove(dest, dialogTitle, length * 2);
    String titleLabel = new String(dest);
    length = XPCOM.strlen_PRUnichar(text);
    dest = new char[length];
    XPCOM.memmove(dest, text, length * 2);
    String textLabel = new String(dest);
    MessageBox messageBox =
        new MessageBox(browser.getShell(), (SWT.OK | SWT.CANCEL) | SWT.ICON_QUESTION);
    messageBox.setText(titleLabel);
    messageBox.setMessage(textLabel);
    int id = messageBox.open();
    int[] result = new int[] {id == SWT.OK ? 1 : 0};
    XPCOM.memmove(_retval, result, 4);
    return XPCOM.NS_OK;
  }
}
