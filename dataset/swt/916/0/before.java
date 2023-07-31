class PlaceHold {
  int okPressed(int widget, int client, int call) {
    String fullPath = null;
    String fileName = null;
    String[] fileNames = null;
    int[] argList = new int[] {OS.XmNdirSpec, 0, OS.XmNdirectory, 0};
    OS.XtGetValues(dialog, argList, argList.length / 2);
    int xmString1 = argList[1];
    Display display = parent.getDisplay();
    int[] table = new int[] {display.tabMapping, display.crMapping};
    int ptr =
        OS.XmStringUnparse(
            xmString1, null, XmCHARSET_TEXT, XmCHARSET_TEXT, table, table.length, XmOUTPUT_ALL);
    if (ptr != 0) {
      int length = OS.strlen(ptr);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, ptr, length);
      OS.XtFree(ptr);
      fullPath = new String(Converter.mbcsToWcs(null, buffer)).trim();
    }
    OS.XmStringFree(xmString1);
    if ((style & SWT.MULTI) != 0) {
      int fileList = OS.XmFileSelectionBoxGetChild(dialog, XmDIALOG_LIST);
      if (fileList == 0) {
        return 0;
      }
      int[] argList2 = new int[] {OS.XmNselectedItems, 0, OS.XmNselectedItemCount, 0};
      OS.XtGetValues(fileList, argList2, argList2.length / 2);
      int items = argList2[1];
      int itemCount = argList2[3];
      int[] buffer1 = new int[1];
      fileNames = new String[itemCount];
      boolean match = false;
      for (int i = 0; i < itemCount; i++) {
        OS.memmove(buffer1, items, 4);
        ptr = buffer1[0];
        int address =
            OS.XmStringUnparse(
                ptr, null, XmCHARSET_TEXT, XmCHARSET_TEXT, table, table.length, XmOUTPUT_ALL);
        if (address != 0) {
          int length = OS.strlen(address);
          byte[] buffer = new byte[length];
          OS.memmove(buffer, address, length);
          OS.XtFree(address);
          String fullFilename = new String(Converter.mbcsToWcs(null, buffer));
          int index = fullFilename.lastIndexOf(SEPARATOR);
          fileNames[i] = fullFilename.substring(index + 1, fullFilename.length());
          if (fullFilename.equals(fullPath)) {
            match = true;
          }
        }
        items += 4;
      }
      if (match) {
        fileName = fileNames[0];
      } else {
        int index = fullPath.lastIndexOf(SEPARATOR);
        fileName = fullPath.substring(index + 1, fullPath.length());
        fileNames = new String[] {fileName};
      }
    } else {
      int index = fullPath.lastIndexOf(SEPARATOR);
      fileName = fullPath.substring(index + 1, fullPath.length());
      fileNames = new String[] {fileName};
    }
    if (fileName.equals("")) {
      int[] argList1 = new int[] {OS.XmNdirMask, 0};
      OS.XtGetValues(dialog, argList1, argList1.length / 2);
      int directoryHandle = argList1[1];
      int[] argList2 = new int[] {OS.XmNpattern, directoryHandle};
      OS.XtSetValues(dialog, argList2, argList2.length / 2);
      OS.XmStringFree(directoryHandle);
      return 0;
    }
    int xmString2 = argList[3];
    ptr =
        OS.XmStringUnparse(
            xmString2, null, XmCHARSET_TEXT, XmCHARSET_TEXT, table, table.length, XmOUTPUT_ALL);
    if (ptr != 0) {
      int length = OS.strlen(ptr);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, ptr, length);
      OS.XtFree(ptr);
      filterPath = new String(Converter.mbcsToWcs(null, buffer));
    }
    OS.XmStringFree(xmString2);
    int length = filterPath.length();
    if (length > 0) {
      if (filterPath.charAt(length - 1) == SEPARATOR) {
        filterPath = filterPath.substring(0, length - 1);
      }
    }
    this.fullPath = fullPath;
    this.fileName = fileName;
    this.fileNames = fileNames;
    OS.XtUnmanageChild(widget);
    return 0;
  }
}
