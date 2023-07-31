class PlaceHold {
  int get_accDescription(long varChild, long pszDescription) {
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    String osDescription = null;
    if (iaccessible != null) {
      code = iaccessible.get_accDescription(varChild, pszDescription);
      if (code == COM.E_INVALIDARG) {
        code = COM.S_FALSE;
      }
      if ((accessibleListenersSize() == 0) && (!(control instanceof Tree))) {
        if (DEBUG) {
          print(
              (((this + ".IAccessible::get_accDescription(") + v.lVal) + ") returning super")
                  + hresult(code));
        }
        return code;
      }
      if (code == COM.S_OK) {
        long[] pDescription = new long[1];
        COM.MoveMemory(pDescription, pszDescription, PTR_SIZEOF);
        int size = COM.SysStringByteLen(pDescription[0]);
        if (size > 0) {
          char[] buffer = new char[(size + 1) / 2];
          COM.MoveMemory(buffer, pDescription[0], size);
          osDescription = new String(buffer);
        }
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
          long hwnd = control.handle;
          long hItem = 0;
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
    for (int i = 0; i < accessibleListenersSize(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getDescription(event);
    }
    if (DEBUG) {
      print(
          ((((this + ".IAccessible::get_accDescription(") + v.lVal) + ") returning ")
                  + event.result)
              + hresult(
                  event.result == null
                      ? code
                      : event.result.length() == 0 ? COM.S_FALSE : COM.S_OK));
    }
    if (event.result == null) {
      return code;
    }
    if (event.result.length() == 0) {
      return COM.S_FALSE;
    }
    setString(pszDescription, event.result);
    return COM.S_OK;
  }
}
