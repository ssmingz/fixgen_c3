class PlaceHold {
  static long callback8(long[] callbackArgs) {
    long address = callbackArgs[0];
    XPCOMObject object = ObjectMap.get(new LONG(address));
    if (object == null) {
      return XPCOM.NS_ERROR_FAILURE;
    }
    long[] args = new long[callbackArgs.length - 1];
    System.arraycopy(callbackArgs, 1, args, 0, args.length);
    return object.method8(args);
  }
}
