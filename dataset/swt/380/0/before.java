class PlaceHold {
  public int InitMouseEvent(
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
      long relatedTargetArg) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + 11,
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
        relatedTargetArg);
  }
}
