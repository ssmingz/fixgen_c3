class PlaceHold {
  void initializeText() {
    int shellHandle;
    int widgetHandle;
    int widgetClass = OS.TopLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(appName, appClass, widgetClass, xDisplay, null, 0);
    widgetHandle = OS.XmCreateScrolledText(shellHandle, null, null, 0);
    OS.XtManageChild(widgetHandle);
    OS.XtSetMappedWhenManaged(shellHandle, false);
    OS.XtRealizeWidget(shellHandle);
    int[] argList =
        new int[] {
          OS.XmNforeground, 0, OS.XmNbackground, 0, OS.XmNfontList, 0, OS.XmNhighlightThickness, 0
        };
    OS.XtGetValues(widgetHandle, argList, argList.length / 2);
    textForeground = argList[1];
    textBackground = argList[3];
    textHighlightThickness = argList[7];
    textFont = Font.motif_new(this, OS.XmFontListCopy(argList[5]));
    OS.XtDestroyWidget(shellHandle);
  }
}
