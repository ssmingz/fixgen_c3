class PlaceHold {
  public void setUp() {
    _model = new DefaultGlobalModel();
    _interactions = ((InteractionsDocument) (_model.getInteractionsDocument()));
    _pane = new InteractionsPane(_model);
    _pane.setCaretPosition(_model.getInteractionsFrozenPos());
    _ready = true;
  }
}
