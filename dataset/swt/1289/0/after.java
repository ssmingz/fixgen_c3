class PlaceHold {
  int okPressed(int widget, int client, int call) {
    extractValues();
    if (!fileNames[0].equals("")) {
      OS.XtUnmanageChild(widget);
      return 0;
    }
    int[] argList1 = new int[] {OS.XmNdirMask, 0};
    OS.XtGetValues(dialog, argList1, argList1.length / 2);
    int directoryHandle = argList1[1];
    int[] argList2 = new int[] {OS.XmNpattern, directoryHandle};
    OS.XtSetValues(dialog, argList2, argList2.length / 2);
    OS.XmStringFree(directoryHandle);
    return 0;
  }
}
