class PlaceHold {
  static long callback47(long[] callbackArgs) {
    long address = callbackArgs[0];
    Object object = ObjectMap.get(new LONG(address));
    if (object == null) {
      return XPCOM.NS_ERROR_FAILURE;
    }
    long[] args = new long[callbackArgs.length - 1];
    System.arraycopy(callbackArgs, 1, args, 0, args.length);
    return ((XPCOMObject) (object)).method47(args);
  }
}
