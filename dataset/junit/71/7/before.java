class PlaceHold {
  private int getConstructorParameterCount() {
    List<ParameterSignature> signatures =
        ParameterSignature.signatures(fClass.getOnlyConstructor());
    int constructorParameterCount = signatures.size();
    return constructorParameterCount;
  }
}
