class PlaceHold {
  protected void backRegion() {
    BrowserHistoryManager rm = _model.getBrowserHistoryManager();
    _frame.addToBrowserHistory();
    BrowserDocumentRegion r = rm.prevCurrentRegion(_frame.getModel().getNotifier());
    updateButtons();
    RegionListUserObj<BrowserDocumentRegion> userObj = getUserObjForRegion(r);
    if (userObj != null) {
      _list.ensureIndexIsVisible(_listModel.indexOf(userObj));
    }
    _frame.scrollToDocumentAndOffset(r.getDocument(), r.getStartOffset(), false, false);
  }
}
