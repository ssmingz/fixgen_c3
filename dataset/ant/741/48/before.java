class PlaceHold {
  public void execute() throws BuildException {
    InputRequest request = null;
    if (validargs != null) {
      Vector accept = StringUtils.split(validargs, ',');
      request = new MultipleChoiceInputRequest(message, accept);
    } else {
      request = new InputRequest(message);
    }
    getProject().getInputHandler().handleInput(request);
    String value = request.getInput();
    if (((value == null) || (value.trim().length() == 0)) && (defaultvalue != null)) {
      value = defaultvalue;
    }
    if (addproperty != null) {
      project.setNewProperty(addproperty, value);
    }
  }
}
