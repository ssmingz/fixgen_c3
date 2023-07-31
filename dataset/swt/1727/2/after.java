class PlaceHold {
  Variant convertToJS(Object value) {
    if (value == null) {
      return new Variant();
    }
    if (value instanceof String) {
      return new Variant(((String) (value)));
    }
    if (value instanceof Boolean) {
      return new Variant(((Boolean) (value)).booleanValue());
    }
    if (value instanceof Number) {
      return new Variant(((Number) (value)).doubleValue());
    }
    if (value instanceof Object[]) {
      Object[] arrayValue = ((Object[]) (value));
      int length = arrayValue.length;
      if (length > 0) {
        IE browser = ((IE) (((Browser) (getParent().getParent())).webBrowser));
        OleAutomation auto = browser.auto;
        int[] rgdispid = auto.getIDsOfNames(new String[] {"Document"});
        if (rgdispid == null) {
          return new Variant();
        }
        Variant pVarResult = auto.getProperty(rgdispid[0]);
        if (pVarResult == null) {
          return new Variant();
        }
        if (pVarResult.getType() == COM.VT_EMPTY) {
          pVarResult.dispose();
          return new Variant();
        }
        OleAutomation document = pVarResult.getAutomation();
        pVarResult.dispose();
        rgdispid = document.getIDsOfNames(new String[] {"parentWindow"});
        if (rgdispid == null) {
          document.dispose();
          return new Variant();
        }
        pVarResult = document.getProperty(rgdispid[0]);
        if ((pVarResult == null) || (pVarResult.getType() == COM.VT_EMPTY)) {
          if (pVarResult != null) {
            pVarResult.dispose();
          }
          document.dispose();
          return new Variant();
        }
        OleAutomation ihtmlWindow2 = pVarResult.getAutomation();
        pVarResult.dispose();
        document.dispose();
        rgdispid = ihtmlWindow2.getIDsOfNames(new String[] {"Array"});
        if (rgdispid == null) {
          ihtmlWindow2.dispose();
          return new Variant();
        }
        Variant arrayType = ihtmlWindow2.getProperty(rgdispid[0]);
        ihtmlWindow2.dispose();
        IDispatch arrayTypeDispatch = arrayType.getDispatch();
        arrayType.dispose();
        int[] result = new int[1];
        int rc = arrayTypeDispatch.QueryInterface(IIDIDispatchEx, result);
        if (rc != COM.S_OK) {
          return new Variant();
        }
        IDispatchEx arrayTypeDispatchEx = new IDispatchEx(result[0]);
        result[0] = 0;
        int resultPtr = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT, sizeof);
        DISPPARAMS params = new DISPPARAMS();
        rc =
            arrayTypeDispatchEx.InvokeEx(
                DISPID_VALUE, LOCALE_USER_DEFAULT, DISPATCH_CONSTRUCT, params, resultPtr, null, 0);
        if (rc != COM.S_OK) {
          OS.GlobalFree(resultPtr);
          return new Variant();
        }
        Variant array = Variant.win32_new(resultPtr);
        OS.GlobalFree(resultPtr);
        auto = array.getAutomation();
        int[] rgdispids = auto.getIDsOfNames(new String[] {"push"});
        if (rgdispids != null) {
          for (int i = 0; i < length; i++) {
            Object currentObject = arrayValue[i];
            try {
              Variant variant = convertToJS(currentObject);
              auto.invoke(rgdispids[0], new Variant[] {variant});
              variant.dispose();
            } catch (SWTException e) {
              auto.dispose();
              array.dispose();
              throw e;
            }
          }
        }
        auto.dispose();
        return array;
      }
    }
    SWT.error(ERROR_INVALID_RETURN_VALUE);
    return null;
  }
}
