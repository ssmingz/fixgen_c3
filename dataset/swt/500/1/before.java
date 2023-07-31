class PlaceHold {
  static int browserProc(int id, int sel, int arg0, int arg1) {
    if (sel == OS.sel_setTag_1) {
      OS.object_setInstanceVariable(id, "tag", arg0);
      return 0;
    }
    int jniRef = OS.objc_msgSend(id, sel_tag);
    if ((jniRef == 0) || (jniRef == (-1))) {
      return 0;
    }
    Safari widget = ((Safari) (OS.JNIGetObject(jniRef)));
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_webView_1didChangeLocationWithinPageForFrame_1) {
      widget.webView_didChangeLocationWithinPageForFrame(arg0, arg1);
    } else if (sel == OS.sel_webView_1didFinishLoadForFrame_1) {
      widget.webView_didFinishLoadForFrame(arg0, arg1);
    } else if (sel == OS.sel_webView_1didStartProvisionalLoadForFrame_1) {
      widget.webView_didStartProvisionalLoadForFrame(arg0, arg1);
    } else if (sel == OS.sel_webView_1didCommitLoadForFrame_1) {
      widget.webView_didCommitLoadForFrame(arg0, arg1);
    } else if (sel == OS.sel_webView_1setFrame_1) {
      widget.webView_setFrame(arg0, arg1);
    } else if (sel == OS.sel_webView_1createWebViewWithRequest_1) {
      return widget.webView_createWebViewWithRequest(arg0, arg1);
    } else if (sel == OS.sel_webView_1setStatusBarVisible_1) {
      widget.webView_setStatusBarVisible(arg0, arg1);
    } else if (sel == OS.sel_webView_1setResizable_1) {
      widget.webView_setResizable(arg0, arg1);
    } else if (sel == OS.sel_webView_1setStatusText_1) {
      widget.webView_setStatusText(arg0, arg1);
    } else if (sel == OS.sel_webView_1setToolbarsVisible_1) {
      widget.webView_setToolbarsVisible(arg0, arg1);
    } else if (sel == OS.sel_webView_1runJavaScriptAlertPanelWithMessage_1) {
      widget.webView_runJavaScriptAlertPanelWithMessage(arg0, arg1);
    } else if (sel == OS.sel_webView_1runJavaScriptConfirmPanelWithMessage_1) {
      return widget.webView_runJavaScriptConfirmPanelWithMessage(arg0, arg1);
    } else if (sel == OS.sel_webView_1runOpenPanelForFileButtonWithResultListener_1) {
      widget.webView_runOpenPanelForFileButtonWithResultListener(arg0, arg1);
    } else if (sel == OS.sel_download_1decideDestinationWithSuggestedFilename_1) {
      widget.download_decideDestinationWithSuggestedFilename(arg0, arg1);
    }
    return 0;
  }
}
