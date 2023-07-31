class PlaceHold {
  void deregister() {
    super.deregister();
    int[] argList = new int[] {OS.XmNtextField, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    display.removeWidget(argList[1]);
  }
}
