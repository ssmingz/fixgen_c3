class PlaceHold {
  public int InitNSMouseEvent(
      long typeArg,
      int canBubbleArg,
      int cancelableArg,
      long viewArg,
      int detailArg,
      int screenXArg,
      int screenYArg,
      int clientXArg,
      int clientYArg,
      int ctrlKeyArg,
      int altKeyArg,
      int shiftKeyArg,
      int metaKeyArg,
      short buttonArg,
      long relatedTargetArg,
      float pressure,
      short inputSourceArg) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner17 ? 17 : 14),
        getAddress(),
        typeArg,
        canBubbleArg,
        cancelableArg,
        viewArg,
        detailArg,
        screenXArg,
        screenYArg,
        clientXArg,
        clientYArg,
        ctrlKeyArg,
        altKeyArg,
        shiftKeyArg,
        metaKeyArg,
        buttonArg,
        relatedTargetArg,
        pressure,
        inputSourceArg);
  }
}
