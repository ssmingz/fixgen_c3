class PlaceHold {
  public id validRequestorForSendType(NSString sendType, NSString returnType) {
    int result =
        OS.objc_msgSend(
            this.id,
            sel_validRequestorForSendType_returnType_,
            sendType != null ? sendType.id : 0,
            returnType != null ? returnType.id : 0);
    return result != 0 ? new id(result) : null;
  }
}
