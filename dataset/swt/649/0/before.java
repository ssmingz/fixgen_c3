class PlaceHold {
  public WebView initWithFrame(NSRect frame, NSString frameName, NSString groupName) {
    int result =
        OS.objc_msgSend(
            this.id,
            sel_initWithFrame_frameName_groupName_,
            frame,
            frameName != null ? frameName.id : 0,
            groupName != null ? groupName.id : 0);
    return result == this.id ? this : result != 0 ? new WebView(result) : null;
  }
}
