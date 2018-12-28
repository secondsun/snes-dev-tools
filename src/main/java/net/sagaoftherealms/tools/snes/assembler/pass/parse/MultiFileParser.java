package net.sagaoftherealms.tools.snes.assembler.pass.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.sagaoftherealms.tools.snes.assembler.definition.directives.AllDirectives;
import net.sagaoftherealms.tools.snes.assembler.definition.opcodes.OpCode;
import net.sagaoftherealms.tools.snes.assembler.main.Flags;
import net.sagaoftherealms.tools.snes.assembler.main.InputData;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.DirectiveNode;
import net.sagaoftherealms.tools.snes.assembler.pass.parse.directive.macro.MacroNode;

public class MultiFileParser {

  private final OpCode[] opcodes;

  private final Map<String, List<Node>> parsedFiles = new HashMap<>();
  private final Set<String> filesToParse = new HashSet<>();
  private final Map<String, Optional<MacroNode>> macroNames = new HashMap<>();


  public MultiFileParser(OpCode[] opcodes) {
    this.opcodes = opcodes;
  }

  public void parse(final String sourceDirectory, final String rootSourceFile) {
    preParse(sourceDirectory, rootSourceFile, new HashSet<>());
    parseFile(sourceDirectory, rootSourceFile);
    while (!filesToParse.isEmpty()) {
      List<String> filesList = new ArrayList<>(filesToParse);
      filesToParse.clear();
      for (String fileToParse : filesList) {
        parseFile(sourceDirectory, fileToParse);
      }
    }
  }

  /**
   * Scans the entire source path and sets maco names for lookup during parsing.
   *
   * @param sourceDirectory directory relative to pwd
   * @param rootSourceFile the filename
   */
  private void preParse(String sourceDirectory, String rootSourceFile, HashSet<String> scannedIncludes) {
    var parser = makeParser(sourceDirectory, rootSourceFile);

    var includesToScan = parser.getIncludes();
    macroNames.putAll(parser.getMacroMap());

    includesToScan.forEach(fileName -> {
      if (!scannedIncludes.contains(fileName)) {
        preParse(sourceDirectory,fileName,scannedIncludes);
        scannedIncludes.add(fileName);
      }
    });

  }

  private void parseFile(String sourceDirectory, final String rootSourceFile) {
    var fileName = sourceDirectory + File.separator + rootSourceFile;
    var parser = makeParser(sourceDirectory, rootSourceFile);

    List<Node> newList = new ArrayList<>();
    Node node = parser.nextNode();
    while (node != null) {
      if (node.getType().equals(NodeTypes.DIRECTIVE)
          && ((DirectiveNode) node).getDirectiveType().equals(AllDirectives.INCLUDE)) {
        scheduleParse((DirectiveNode) node);
      }
      newList.add(node);
      node = parser.nextNode();
    }
    parsedFiles.put(fileName, newList);
  }

  private SourceParser makeParser(String sourceDirectory, String rootSourceFile) {
    var fileName = sourceDirectory + File.separator + rootSourceFile;

    var stream = getClass().getClassLoader().getResourceAsStream(fileName);
    final String outfile = "test.out";

    var data = new InputData(new Flags(outfile));

    data.includeFile(stream, rootSourceFile, 0);

    var scanner = data.startRead(opcodes);
    var parser = new SourceParser(scanner, macroNames);
    return parser;
  }

  private void scheduleParse(DirectiveNode includeDirectiveNode) {
    String filename = includeDirectiveNode.getArguments().getString(0);
    if (parsedFiles.get(includeDirectiveNode) == null) {
      filesToParse.add(filename);
    }
  }

  public List<Node> getNodes(String includedFile) {
    return parsedFiles.get(includedFile);
  }
}
