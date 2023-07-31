class PlaceHold {
  void createWidget(int index) {
    super.createWidget(index);
    hiddenText = "";
    if ((style & SWT.PASSWORD) != 0) {
      setEchoChar('*');
    }
  }
}
