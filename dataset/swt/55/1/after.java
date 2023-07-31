class PlaceHold {
  static long callback76(long[] callbackArgs) {
    long address = callbackArgs[0];
    COMObject object = ObjectMap.get(new LONG(address));
    if (object == null) {
      return COM.E_FAIL;
    }
    long[] args = new long[callbackArgs.length - 1];
    System.arraycopy(callbackArgs, 1, args, 0, args.length);
    return object.method76(args);
  }
}
