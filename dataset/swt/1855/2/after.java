class PlaceHold {
  int get_accDescription(int varChild, int pszDescription) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    String osDescription = null;
    int code = iaccessible.get_accDescription(varChild, pszDescription);
    if (code == COM.E_INVALIDARG) {
      code = COM.S_FALSE;
    }
    if ((accessibleListeners.size() == 0) && (!(control instanceof Tree))) {
      return code;
    }
    if (code == COM.S_OK) {
      int[] pDescription = new int[1];
      COM.MoveMemory(pDescription, pszDescription, PTR_SIZEOF);
      int size = COM.SysStringByteLen(pDescription[0]);
      if (size > 0) {
        char[] buffer = new char[(size + 1) / 2];
        COM.MoveMemory(buffer, pDescription[0], size);
        osDescription = new String(buffer);
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osDescription;
    if (v.lVal != COM.CHILDID_SELF) {
      if (control instanceof Tree) {
        Tree tree = ((Tree) (control));
        int columnCount = tree.getColumnCount();
        if (columnCount > 1) {
          int hwnd = control.handle;
          int hItem = 0;
          if (OS.COMCTL32_MAJOR >= 6) {
            hItem = OS.SendMessage(hwnd, TVM_MAPACCIDTOHTREEITEM, v.lVal, 0);
          } else {
            hItem = v.lVal;
          }
          Widget widget = tree.getDisplay().findWidget(hwnd, hItem);
          event.result = "";
          if ((widget != null) && (widget instanceof TreeItem)) {
            TreeItem item = ((TreeItem) (widget));
            for (int i = 1; i < columnCount; i++) {
              event.result += (tree.getColumn(i).getText() + ": ") + item.getText(i);
              if ((i + 1) < columnCount) {
                event.result += ", ";
              }
            }
          }
        }
      }
    }
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getDescription(event);
    }
    if (event.result == null) {
      return code;
    }
    char[] data = (event.result + "\u0000").toCharArray();
    int ptr = COM.SysAllocString(data);
    COM.MoveMemory(pszDescription, new int[] {ptr}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
