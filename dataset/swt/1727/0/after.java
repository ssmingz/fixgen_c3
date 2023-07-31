class PlaceHold {
  nsIVariant convertToJS(Object value, nsIComponentManager componentManager) {
    int[] result = new int[1];
    byte[] aContractID = MozillaDelegate.wcsToMbcs(null, NS_VARIANT_CONTRACTID, true);
    int rc =
        componentManager.CreateInstanceByContractID(
            aContractID, 0, NS_IWRITABLEVARIANT_IID, result);
    nsIWritableVariant variant = new nsIWritableVariant(result[0]);
    result[0] = 0;
    if (value == null) {
      rc = variant.SetAsVoid();
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      return variant;
    }
    if (value instanceof String) {
      String stringValue = ((String) (value));
      int length = stringValue.length();
      char[] chars = new char[length];
      stringValue.getChars(0, length, chars, 0);
      rc = variant.SetAsWStringWithSize(length, chars);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      return variant;
    }
    if (value instanceof Boolean) {
      Boolean booleanValue = ((Boolean) (value));
      rc = variant.SetAsBool(booleanValue.booleanValue() ? 1 : 0);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      return variant;
    }
    if (value instanceof Number) {
      Number numberValue = ((Number) (value));
      rc = variant.SetAsDouble(numberValue.doubleValue());
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      return variant;
    }
    if (value instanceof Object[]) {
      Object[] arrayValue = ((Object[]) (value));
      int length = arrayValue.length;
      if (length > 0) {
        int arrayPtr = C.malloc(C.PTR_SIZEOF * length);
        for (int i = 0; i < length; i++) {
          Object currentObject = arrayValue[i];
          try {
            nsIVariant currentVariant = convertToJS(currentObject, componentManager);
            C.memmove(
                arrayPtr + (C.PTR_SIZEOF * i), new int[] {currentVariant.getAddress()}, PTR_SIZEOF);
          } catch (SWTException e) {
            C.free(arrayPtr);
            variant.Release();
            for (int j = 0; j < i; j++) {
              int[] ptr = new int[1];
              C.memmove(ptr, arrayPtr + (C.PTR_SIZEOF * j), PTR_SIZEOF);
              new nsISupports(ptr[0]).Release();
            }
            throw e;
          }
        }
        int idPtr = C.malloc(sizeof);
        XPCOM.memmove(idPtr, NS_IVARIANT_IID, sizeof);
        rc = variant.SetAsArray(VTYPE_INTERFACE_IS, idPtr, length, arrayPtr);
        C.free(idPtr);
        C.free(arrayPtr);
        if (rc != XPCOM.NS_OK) {
          Mozilla.error(rc);
        }
        return variant;
      }
    }
    variant.Release();
    SWT.error(ERROR_INVALID_RETURN_VALUE);
    return null;
  }
}
