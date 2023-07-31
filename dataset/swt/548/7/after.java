class PlaceHold {
  public int CreateInstance(int aOuter, int iid, int result) {
    PromptService promptService = new PromptService();
    promptService.AddRef();
    XPCOM.memmove(result, new int[] {promptService.getAddress()}, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
