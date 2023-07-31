class PlaceHold {
  void createWidget(int index) {
    super.createWidget(index);
    OS.XmDropSiteUnregister(handle);
    hiddenText = "";
    if ((style & SWT.PASSWORD) != 0) {
      setEchoChar('*');
    }
  }
}
