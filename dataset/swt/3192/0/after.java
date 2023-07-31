class PlaceHold {
  public int Alert(int parent, int dialogTitle, int text) {
    Browser browser = getBrowser(parent);
    int length = XPCOM.strlen_PRUnichar(dialogTitle);
    char[] dest = new char[length];
    XPCOM.memmove(dest, dialogTitle, length * 2);
    String titleLabel = new String(dest);
    length = XPCOM.strlen_PRUnichar(text);
    dest = new char[length];
    XPCOM.memmove(dest, text, length * 2);
    String textLabel = new String(dest);
    Shell shell = (browser == null) ? new Shell() : browser.getShell();
    MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
    messageBox.setText(titleLabel);
    messageBox.setMessage(textLabel);
    messageBox.open();
    return XPCOM.NS_OK;
  }
}
