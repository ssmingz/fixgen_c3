class PlaceHold {
  void register() {
    super.register();
    int[] argList = new int[] {OS.XmNtextField, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    display.addWidget(argList[1], this);
  }
}
