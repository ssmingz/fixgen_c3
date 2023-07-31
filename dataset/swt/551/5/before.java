class PlaceHold {
  protected Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.pValue == 0)) {
      return null;
    }
    int size = (transferData.format * transferData.length) / 8;
    byte[] buffer = new byte[size];
    OS.memmove(buffer, transferData.pValue, size);
    return buffer;
  }
}
