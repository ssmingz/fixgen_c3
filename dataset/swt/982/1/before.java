class PlaceHold {
  static long callback20(long[] callbackArgs) {
    long address = callbackArgs[0];
    Object object = ObjectMap.get(new LONG(address));
    if (object == null) {
      return COM.E_FAIL;
    }
    long[] args = new long[callbackArgs.length - 1];
    System.arraycopy(callbackArgs, 1, args, 0, args.length);
    return ((COMObject) (object)).method20(args);
  }
}
