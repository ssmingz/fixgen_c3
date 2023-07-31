class PlaceHold {
  static int browserProc(int id, int sel, int arg0, int arg1, int arg2) {
    Widget widget = Display.getCurrent().findWidget(id);
    if (widget == null) {
      return 0;
    }
    Safari safari = ((Safari) (((Browser) (widget)).webBrowser));
    if (sel == OS.sel_webView_didFailProvisionalLoadWithError_forFrame_) {
      safari.webView_didFailProvisionalLoadWithError_forFrame(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_didReceiveTitle_forFrame_) {
      safari.webView_didReceiveTitle_forFrame(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_resource_didFinishLoadingFromDataSource_) {
      safari.webView_resource_didFinishLoadingFromDataSource(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_identifierForInitialRequest_fromDataSource_) {
      return safari.webView_identifierForInitialRequest_fromDataSource(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_contextMenuItemsForElement_defaultMenuItems_) {
      return safari.webView_contextMenuItemsForElement_defaultMenuItems(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_mouseDidMoveOverElement_modifierFlags_) {
      safari.webView_mouseDidMoveOverElement_modifierFlags(arg0, arg1, arg2);
    } else if (sel == OS.sel_webView_unableToImplementPolicyWithError_frame_) {
      safari.webView_unableToImplementPolicyWithError_frame(arg0, arg1, arg2);
    } else if (sel == OS.sel_callJava) {
      id result = safari.callJava(arg0, arg1, arg2);
      return result == null ? 0 : result.id;
    }
    return 0;
  }
}
