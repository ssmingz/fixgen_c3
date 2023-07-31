class PlaceHold {
  void validate(PngFileReadState readState, PngIhdrChunk headerChunk) {
    if (reference.length < MIN_LENGTH) {
      SWT.error(ERROR_INVALID_IMAGE);
    }
    byte[] type = getTypeBytes();
    if (!Character.isUpperCase(((char) (type[2])))) {
      SWT.error(ERROR_INVALID_IMAGE);
    }
    for (int i = 0; i < TYPE_FIELD_LENGTH; i++) {
      if (!Character.isLetter(((char) (type[i])))) {
        SWT.error(ERROR_INVALID_IMAGE);
      }
    }
    if (!checkCRC()) {
      SWT.error(ERROR_INVALID_IMAGE);
    }
  }
}
