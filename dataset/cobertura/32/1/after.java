class PlaceHold {
  public int getCCNForMethod(ClassData classData, String methodName, String methodDescriptor) {
    if (!calculateMethodComplexity) {
      return 0;
    }
    Validate.notNull(classData, "classData must not be null");
    Validate.notNull(methodName, "methodName must not be null");
    Validate.notNull(methodDescriptor, "methodDescriptor must not be null");
    int complexity = 0;
    List<FunctionMetric> methodMetrics =
        getFunctionMetricsForSingleFile(classData.getSourceFileName());
    String goldenMethodName = methodName;
    boolean isConstructor = false;
    if (goldenMethodName.equals("<init>")) {
      isConstructor = true;
      goldenMethodName = classData.getBaseName();
    }
    goldenMethodName = (classData.getName() + ".") + goldenMethodName;
    goldenMethodName = goldenMethodName.replaceAll(Pattern.quote("$"), ".");
    TraceSignatureVisitor v = new TraceSignatureVisitor(Opcodes.ACC_PUBLIC);
    SignatureReader r = new SignatureReader(methodDescriptor);
    r.accept(v);
    String goldenSignature = v.getDeclaration();
    goldenSignature = goldenSignature.substring(1, goldenSignature.length() - 1);
    Map<String, Integer> candidateSignatureToCcn = new HashMap<String, Integer>();
    for (FunctionMetric singleMethodMetrics : methodMetrics) {
      String candidateMethodName =
          singleMethodMetrics.name.substring(0, singleMethodMetrics.name.indexOf('('));
      String candidateSignature =
          stripTypeParameters(
              singleMethodMetrics.name.substring(
                  singleMethodMetrics.name.indexOf('(') + 1,
                  singleMethodMetrics.name.length() - 1));
      if (goldenMethodName.equals(candidateMethodName)) {
        candidateSignatureToCcn.put(candidateSignature, singleMethodMetrics.ccn);
      }
    }
    if (candidateSignatureToCcn.size() == 1) {
      return candidateSignatureToCcn.values().iterator().next();
    }
    if (!goldenSignature.isEmpty()) {
      try {
        String[] goldenParameterTypeStrings = goldenSignature.split(",");
        Class<?>[] goldenParameterTypes = new Class[goldenParameterTypeStrings.length];
        for (int i = 0; i < goldenParameterTypeStrings.length; i++) {
          goldenParameterTypes[i] =
              ClassUtils.getClass(goldenParameterTypeStrings[i].trim(), false);
        }
        Class<?> klass = ClassUtils.getClass(classData.getName(), false);
        if (isConstructor) {
          Constructor<?> realMethod = klass.getDeclaredConstructor(goldenParameterTypes);
          goldenSignature = realMethod.toGenericString();
        } else {
          Method realMethod = klass.getDeclaredMethod(methodName, goldenParameterTypes);
          goldenSignature = realMethod.toGenericString();
        }
        goldenSignature = goldenSignature.replaceAll("\\.\\.\\.", "[]");
        goldenSignature =
            goldenSignature.substring(
                goldenSignature.indexOf("(") + 1, goldenSignature.length() - 1);
        goldenSignature = stripTypeParameters(goldenSignature);
      } catch (Exception e) {
        logger.error("Error while getting method CC for " + goldenMethodName, e);
        return 0;
      }
    }
    goldenSignature = goldenSignature.replaceAll(Pattern.quote("$"), ".");
    double signatureMatchPercentTillNow = 0;
    for (Entry<String, Integer> candidateSignatureToCcnEntry : candidateSignatureToCcn.entrySet()) {
      String candidateSignature = candidateSignatureToCcnEntry.getKey();
      double currentMatchPercent = matchSignatures(candidateSignature, goldenSignature);
      if (currentMatchPercent == 1) {
        return candidateSignatureToCcnEntry.getValue();
      }
      if (currentMatchPercent > signatureMatchPercentTillNow) {
        complexity = candidateSignatureToCcnEntry.getValue();
        signatureMatchPercentTillNow = currentMatchPercent;
      }
    }
    return complexity;
  }
}
