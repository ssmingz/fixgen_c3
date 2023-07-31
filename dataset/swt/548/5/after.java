class PlaceHold {
  int Next(int celt, int rgvar, int pceltFetched) {
    if (accessibleControlListeners.size() == 0) {
      int[] ppvObject = new int[1];
      int code = iaccessible.QueryInterface(IIDIEnumVARIANT, ppvObject);
      if (code != COM.S_OK) {
        return code;
      }
      IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
      int[] celtFetched = new int[1];
      code = ienumvariant.Next(celt, rgvar, celtFetched);
      ienumvariant.Release();
      COM.MoveMemory(pceltFetched, celtFetched, 4);
      return code;
    }
    if (rgvar == 0) {
      return COM.E_INVALIDARG;
    }
    if ((pceltFetched == 0) && (celt != 1)) {
      return COM.E_INVALIDARG;
    }
    if (enumIndex == 0) {
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = ACC.CHILDID_SELF;
      for (int i = 0; i < accessibleControlListeners.size(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getChildren(event);
      }
      variants = event.children;
    }
    Object[] nextItems = null;
    if ((variants != null) && (celt >= 1)) {
      int endIndex = (enumIndex + celt) - 1;
      if (endIndex > (variants.length - 1)) {
        endIndex = variants.length - 1;
      }
      if (enumIndex <= endIndex) {
        nextItems = new Object[(endIndex - enumIndex) + 1];
        for (int i = 0; i < nextItems.length; i++) {
          Object child = variants[enumIndex];
          if (child instanceof Integer) {
            nextItems[i] = new Integer(childIDToOs(((Integer) (child)).intValue()));
          } else {
            nextItems[i] = child;
          }
          enumIndex++;
        }
      }
    }
    if (nextItems != null) {
      for (int i = 0; i < nextItems.length; i++) {
        Object nextItem = nextItems[i];
        if (nextItem instanceof Integer) {
          int item = ((Integer) (nextItem)).intValue();
          COM.MoveMemory(rgvar + (i * 16), new short[] {COM.VT_I4}, 2);
          COM.MoveMemory((rgvar + (i * 16)) + 8, new int[] {item}, 4);
        } else {
          Accessible accessible = ((Accessible) (nextItem));
          accessible.AddRef();
          COM.MoveMemory(rgvar + (i * 16), new short[] {COM.VT_DISPATCH}, 2);
          COM.MoveMemory(
              (rgvar + (i * 16)) + 8,
              new int[] {accessible.objIAccessible.getAddress()},
              PTR_SIZEOF);
        }
      }
      if (pceltFetched != 0) {
        COM.MoveMemory(pceltFetched, new int[] {nextItems.length}, 4);
      }
      if (nextItems.length == celt) {
        return COM.S_OK;
      }
    } else if (pceltFetched != 0) {
      COM.MoveMemory(pceltFetched, new int[] {0}, 4);
    }
    return COM.S_FALSE;
  }
}
