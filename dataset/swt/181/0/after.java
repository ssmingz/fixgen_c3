class PlaceHold {
  void register() {
    super.register();
    int[] argList = new int[] {OS.XmNlist, 0, OS.XmNtextField, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    display.addWidget(argList[1], this);
    display.addWidget(argList[3], this);
  }
}
